package com.zetheta.paymentorchestration.service;

import com.zetheta.paymentorchestration.dto.PaymentRequest;
import com.zetheta.paymentorchestration.gateway.PaymentGateway;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoutingService {

    private final List<PaymentGateway> gateways;
    private final GatewayHealthService gatewayHealthService;

    public RoutingService(
            List<PaymentGateway> gateways,
            GatewayHealthService gatewayHealthService) {

        this.gateways = gateways;
        this.gatewayHealthService = gatewayHealthService;
    }

    public PaymentGateway selectGateway(
            PaymentRequest request) {

        String preferredGateway;

        // UPI for small payments
        if (request.getAmount() < 500) {

            preferredGateway = "UPI";

        }
        // Razorpay
        else if (request.getAmount() < 1000) {

            preferredGateway = "RAZORPAY";

        }
        // Stripe
        else if (request.getAmount() < 5000) {

            preferredGateway = "STRIPE";

        }
        // PayU
        else {

            preferredGateway = "PAYU";
        }

        // Check preferred gateway health
        boolean isUp =
                gatewayHealthService.isGatewayUp(
                        preferredGateway);

        // Use preferred gateway if healthy
        if (isUp) {

            return gateways.stream()
                    .filter(g ->
                            g.getGatewayName()
                                    .equals(preferredGateway))
                    .findFirst()
                    .orElseThrow(() ->
                            new RuntimeException(
                                    "Preferred Gateway Not Found"));
        }

        // Failover to another healthy gateway
        return gateways.stream()
                .filter(g ->
                        gatewayHealthService.isGatewayUp(
                                g.getGatewayName()))
                .findFirst()
                .orElseThrow(() ->
                        new RuntimeException(
                                "No Healthy Gateway Available"));
    }
}