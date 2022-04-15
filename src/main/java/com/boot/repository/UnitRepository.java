package com.boot.repository;

import com.boot.entity.Suppliers;
import com.boot.entity.Unit;
import com.boot.entity.UnitProductions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UnitRepository extends JpaRepository<Unit, Long> {
    List<Unit> findByTypeTrue();

    @Query(value = "SELECT U FROM Unit U WHERE UPPER(U.name) LIKE UPPER(?1)", nativeQuery = true)
    List<Unit> findByName(String search);
}
