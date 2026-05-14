package com.zetheta.paymentorchestration.gateway;

import com.zetheta.paymentorchestration.dto.PaymentRequest;

public interface PaymentGateway {

    String processPayment(PaymentRequest request);

    String getGatewayName();
}