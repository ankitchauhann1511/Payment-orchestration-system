package com.zetheta.paymentorchestration.gateway;

import com.zetheta.paymentorchestration.dto.PaymentRequest;
import org.springframework.stereotype.Component;

@Component
public class PayUGateway implements PaymentGateway {

    @Override
    public String processPayment(PaymentRequest request) {

        return "Payment Success via PayU";
    }

    @Override
    public String getGatewayName() {

        return "PAYU";
    }
}