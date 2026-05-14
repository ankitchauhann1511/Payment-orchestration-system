package com.zetheta.paymentorchestration.gateway;

import com.zetheta.paymentorchestration.dto.PaymentRequest;
import org.springframework.stereotype.Component;

@Component
public class RazorpayGateway implements PaymentGateway {

    @Override
    public String processPayment(PaymentRequest request) {

        return "Payment Success via Razorpay";
    }

    @Override
    public String getGatewayName() {

        return "RAZORPAY";
    }
}