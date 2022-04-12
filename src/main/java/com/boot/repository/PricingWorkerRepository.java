package com.boot.repository;

import com.boot.entity.PricingWorker;
import com.boot.entity.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PricingWorkerRepository extends JpaRepository<PricingWorker, Long> {
}
