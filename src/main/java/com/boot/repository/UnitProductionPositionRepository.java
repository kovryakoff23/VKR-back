package com.boot.repository;

import com.boot.entity.UnitProductionPosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnitProductionPositionRepository extends JpaRepository<UnitProductionPosition, Long> {
}
