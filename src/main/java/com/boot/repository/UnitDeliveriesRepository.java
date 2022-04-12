package com.boot.repository;

import com.boot.entity.Unit;
import com.boot.entity.UnitDeliveries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnitDeliveriesRepository  extends JpaRepository<UnitDeliveries, Long> {
}
