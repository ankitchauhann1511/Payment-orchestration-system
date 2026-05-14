package com.zetheta.paymentorchestration.controller;

import com.zetheta.paymentorchestration.dto.PaymentRequest;
import com.zetheta.paymentorchestration.service.PaymentService;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public String makePayment(@RequestBody PaymentRequest request) {

        return paymentService.processPayment(request);
    }
}