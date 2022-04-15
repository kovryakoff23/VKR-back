package com.boot.repository;

import com.boot.entity.PaymentSupplier;
import com.boot.entity.UnitProductions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UnitProductionRepository extends JpaRepository<UnitProductions, Long>{
    List<UnitProductions> findByStatusTrue();
}
