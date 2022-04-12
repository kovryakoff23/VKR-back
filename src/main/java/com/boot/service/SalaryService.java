package com.boot.service;

import com.boot.component.Salary;
import com.boot.entity.SalaryWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SalaryService {
    @Autowired
    SalaryWorkerService salaryWorkerService;
    @Autowired
    WorkerService workerService;

    public Set<Salary> getAll(){
        Set<Salary> salaries = workerService.getAll().stream()
                .map(value->{
                    return new Salary(value,
                            value
                                    .getSalaryWorkers()
                                    .stream()
                                    .filter(SalaryWorker::isStatus)
                                    .map(SalaryWorker::getSumSalary)
                                    .mapToLong(Long::longValue)
                                    .sum());
        })
                .filter(value->value.getSumSalary()>0&&value.getSumSalary()!=null)
                .collect(Collectors.toSet());
        return salaries;
    }
    public Set<Salary> getAllByDate(Date dateStartSalary, Date dateEndSalary){
        Set<Salary> salaries = workerService.getAll().stream()
                .map(value->{
                    return new Salary(value,
                            value
                                    .getSalaryWorkers()
                                    .stream()
                                    .filter(SalaryWorker::isStatus)
                                    .filter(value1->value1.getDateSalary().after(dateStartSalary))
                                    .filter(value1->value1.getDateSalary().before(dateEndSalary))
                                    .filter(SalaryWorker::isStatus)
                                    .map(SalaryWorker::getSumSalary)
                                    .mapToLong(Long::longValue)
                                    .sum());
                })
                .filter(value->value.getSumSalary()>0&&value.getSumSalary()!=null)
                .collect(Collectors.toSet());
        return salaries;
    }
}
