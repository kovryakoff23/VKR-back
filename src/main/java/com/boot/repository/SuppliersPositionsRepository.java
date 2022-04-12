package com.boot.repository;

import com.boot.entity.SupplierPositions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuppliersPositionsRepository  extends JpaRepository<SupplierPositions, Long> {
}
