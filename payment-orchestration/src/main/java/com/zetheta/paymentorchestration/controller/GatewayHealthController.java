package com.zetheta.paymentorchestration.controller;

import com.zetheta.paymentorchestration.service.GatewayHealthService;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/gateway")
public class GatewayHealthController {

    private final GatewayHealthService service;

    public GatewayHealthController(GatewayHealthService service) {
        this.service = service;
    }

    @PostMapping("/down/{gatewayName}")
    public String markDown(@PathVariable String gatewayName) {

        return service.markGatewayDown(gatewayName);
    }

    @PostMapping("/up/{gatewayName}")
    public String markUp(@PathVariable String gatewayName) {

        return service.markGatewayUp(gatewayName);
    }
}