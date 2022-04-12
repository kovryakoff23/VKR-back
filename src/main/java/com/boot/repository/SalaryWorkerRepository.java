package com.boot.repository;


import com.boot.entity.SalaryWorker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalaryWorkerRepository extends JpaRepository<SalaryWorker, Long> {

}
