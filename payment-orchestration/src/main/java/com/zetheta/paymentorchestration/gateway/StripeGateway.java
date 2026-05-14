package com.zetheta.paymentorchestration.gateway;

import com.zetheta.paymentorchestration.dto.PaymentRequest;
import org.springframework.stereotype.Component;

@Component
public class StripeGateway implements PaymentGateway {

    @Override
    public String processPayment(PaymentRequest request) {

        return "Payment Success via Stripe";
    }

    @Override
    public String getGatewayName() {

        return "STRIPE";
    }
}