package com.boot.repository;


import com.boot.entity.UnitUpkeep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnitUpkeepRepository extends JpaRepository<UnitUpkeep, Long> {
}
