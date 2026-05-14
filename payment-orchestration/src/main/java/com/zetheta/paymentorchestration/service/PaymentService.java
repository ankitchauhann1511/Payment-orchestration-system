package com.zetheta.paymentorchestration.service;

import com.zetheta.paymentorchestration.dto.PaymentRequest;
import com.zetheta.paymentorchestration.entity.Payment;
import com.zetheta.paymentorchestration.entity.TransactionStatus;
import com.zetheta.paymentorchestration.gateway.PaymentGateway;
import com.zetheta.paymentorchestration.repository.PaymentRepository;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentService {

    private final RoutingService routingService;
    private final PaymentRepository paymentRepository;

    public PaymentService(RoutingService routingService,
                          PaymentRepository paymentRepository) {

        this.routingService = routingService;
        this.paymentRepository = paymentRepository;
    }

    public String processPayment(PaymentRequest request) {

        // Check duplicate payment
        Optional<Payment> existingPayment =
                paymentRepository.findByIdempotencyKey(
                        request.getIdempotencyKey());

        if (existingPayment.isPresent()) {

            return "Duplicate Payment Request";
        }

        Payment payment = new Payment();

        payment.setOrderId(request.getOrderId());
        payment.setAmount(request.getAmount());
        payment.setCurrency(request.getCurrency());
        payment.setIdempotencyKey(
                request.getIdempotencyKey());

        // Initial State
        payment.setStatus(TransactionStatus.CREATED);

        paymentRepository.save(payment);

        int retryCount = 3;

        while (retryCount > 0) {

            try {

                // Processing State
                payment.setStatus(
                        TransactionStatus.PROCESSING);

                paymentRepository.save(payment);

                PaymentGateway gateway =
                        routingService.selectGateway(request);

                String result =
                        gateway.processPayment(request);

                payment.setGateway(
                        gateway.getGatewayName());

                // Success State
                payment.setStatus(
                        TransactionStatus.SUCCESS);

                paymentRepository.save(payment);

                return result;

            } catch (Exception ex) {

                retryCount--;

                System.out.println(
                        "Retry Attempt Remaining: "
                                + retryCount);

                if (retryCount == 0) {

                    // Failed State
                    payment.setStatus(
                            TransactionStatus.FAILED);

                    paymentRepository.save(payment);
                }
            }
        }

        return "Payment Failed After Retries";
    }
}