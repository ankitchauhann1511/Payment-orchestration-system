package com.zetheta.paymentorchestration.service;

import com.zetheta.paymentorchestration.entity.GatewayHealth;
import com.zetheta.paymentorchestration.entity.GatewayStatus;
import com.zetheta.paymentorchestration.repository.GatewayHealthRepository;

import org.springframework.stereotype.Service;

@Service
public class GatewayHealthService {

    private final GatewayHealthRepository repository;

    public GatewayHealthService(GatewayHealthRepository repository) {
        this.repository = repository;
    }

    public String markGatewayDown(String gatewayName) {

        GatewayHealth gatewayHealth = repository
                .findByGatewayName(gatewayName)
                .orElse(new GatewayHealth());

        gatewayHealth.setGatewayName(gatewayName);
        gatewayHealth.setStatus(GatewayStatus.DOWN);

        repository.save(gatewayHealth);

        return gatewayName + " marked DOWN";
    }

    public String markGatewayUp(String gatewayName) {

        GatewayHealth gatewayHealth = repository
                .findByGatewayName(gatewayName)
                .orElse(new GatewayHealth());

        gatewayHealth.setGatewayName(gatewayName);
        gatewayHealth.setStatus(GatewayStatus.UP);

        repository.save(gatewayHealth);

        return gatewayName + " marked UP";
    }

    public boolean isGatewayUp(String gatewayName) {

        return repository.findByGatewayName(gatewayName)
                .map(g -> g.getStatus().name().equals("UP"))
                .orElse(true);
    }
}