package com.zetheta.paymentorchestration.controller;

import com.zetheta.paymentorchestration.dto.WebhookRequest;
import com.zetheta.paymentorchestration.service.WebhookService;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/webhooks")
public class WebhookController {

    private final WebhookService webhookService;

    public WebhookController(
            WebhookService webhookService) {

        this.webhookService = webhookService;
    }

    @PostMapping("/payment")
    public String receiveWebhook(
            @RequestBody WebhookRequest request) {

        return webhookService
                .processWebhook(request);
    }
}