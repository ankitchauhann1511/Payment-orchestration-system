package com.zetheta.paymentorchestration.gateway;

import com.zetheta.paymentorchestration.dto.PaymentRequest;

import org.springframework.stereotype.Component;

@Component
public class UPIGateway implements PaymentGateway {

    @Override
    public String processPayment(
            PaymentRequest request) {

        return "Payment Success via UPI";
    }

    @Override
    public String getGatewayName() {

        return "UPI";
    }
}