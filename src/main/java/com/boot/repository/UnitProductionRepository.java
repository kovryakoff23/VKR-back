package com.boot.repository;

import com.boot.entity.UnitProductions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnitProductionRepository extends JpaRepository<UnitProductions, Long>{
//    @Query("Select s from Unit_production_works s where s.unit_id = ?1")
//    List<UnitProductionWorks> findAllById(Long unitId);
}
