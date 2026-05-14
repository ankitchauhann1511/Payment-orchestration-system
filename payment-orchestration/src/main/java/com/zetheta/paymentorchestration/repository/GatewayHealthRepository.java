package com.zetheta.paymentorchestration.repository;

import com.zetheta.paymentorchestration.entity.GatewayHealth;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GatewayHealthRepository
        extends JpaRepository<GatewayHealth, Long> {

    Optional<GatewayHealth> findByGatewayName(String gatewayName);
}