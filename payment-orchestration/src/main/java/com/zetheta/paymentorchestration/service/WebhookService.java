package com.zetheta.paymentorchestration.service;

import com.zetheta.paymentorchestration.dto.WebhookRequest;
import com.zetheta.paymentorchestration.entity.Payment;
import com.zetheta.paymentorchestration.entity.TransactionStatus;
import com.zetheta.paymentorchestration.entity.WebhookEvent;
import com.zetheta.paymentorchestration.repository.PaymentRepository;
import com.zetheta.paymentorchestration.repository.WebhookEventRepository;

import org.springframework.stereotype.Service;

@Service
public class WebhookService {

    private final WebhookEventRepository webhookRepository;
    private final PaymentRepository paymentRepository;

    public WebhookService(
            WebhookEventRepository webhookRepository,
            PaymentRepository paymentRepository) {

        this.webhookRepository = webhookRepository;
        this.paymentRepository = paymentRepository;
    }

    public String processWebhook(
            WebhookRequest request) {

        // Deduplication
        boolean exists = webhookRepository
                .findByEventId(request.getEventId())
                .isPresent();

        if (exists) {

            return "Duplicate Webhook Ignored";
        }

        // Save event
        WebhookEvent event = new WebhookEvent();

        event.setEventId(request.getEventId());
        event.setOrderId(request.getOrderId());
        event.setGateway(request.getGateway());

        webhookRepository.save(event);

        // Update payment
        Payment payment = paymentRepository
                .findByOrderId(request.getOrderId())
                .orElseThrow(() ->
                        new RuntimeException(
                                "Payment Not Found"));

        payment.setGateway(request.getGateway());

        payment.setStatus(
                TransactionStatus.valueOf(
                        request.getStatus()));

        paymentRepository.save(payment);

        return "Webhook Processed Successfully";
    }
}