package com.boot.repository;

import com.boot.entity.Unit;
import com.boot.entity.UnitDeliveriesPosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnitDeliveriesPositionRepository  extends JpaRepository<UnitDeliveriesPosition, Long> {
}
