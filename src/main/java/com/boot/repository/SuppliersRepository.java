package com.boot.repository;

import com.boot.entity.Suppliers;
import com.boot.entity.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuppliersRepository  extends JpaRepository<Suppliers, Long> {
}
