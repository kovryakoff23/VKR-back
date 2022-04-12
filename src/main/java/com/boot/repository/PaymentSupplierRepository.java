package com.boot.repository;

import com.boot.entity.Documentation;
import com.boot.entity.PaymentSupplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentSupplierRepository extends JpaRepository<PaymentSupplier, Long> {
}
