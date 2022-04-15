package com.boot.service;

import com.boot.entity.SalaryWorker;
import com.boot.entity.Unit;
import com.boot.repository.SalaryWorkerRepository;
import lombok.Synchronized;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SalaryWorkerService implements ServiceMag<SalaryWorker> {
    SalaryWorkerRepository salaryWorkerRepository;
    WorkerService workerService;

    @Autowired
    public SalaryWorkerService(SalaryWorkerRepository salaryWorkerRepository, WorkerService workerService) {
        this.salaryWorkerRepository = salaryWorkerRepository;
        this.workerService = workerService;
    }

    @Override
    public SalaryWorker get(long id) {
        return salaryWorkerRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public List<SalaryWorker> getAll() {
        return salaryWorkerRepository.findAll();
    }

    @Override
    public void save(SalaryWorker entity) {
        salaryWorkerRepository.save(entity);
    }

    public void update(SalaryWorker entity) {
        salaryWorkerRepository.save(entity);
    }
    @Override
    public void delete(long id) {
        salaryWorkerRepository.deleteById(id);
    }

    public Set<SalaryWorker> getAllByDate(Date dateStartSalary, Date dateEndSalary, Long workerId){
        return workerService.get(workerId).getSalaryWorkers().stream()
                .filter(SalaryWorker::isStatus)
                .filter(value1->value1.getDateSalary().after(dateStartSalary))
                .filter(value1->value1.getDateSalary().before(dateEndSalary))
                .collect(Collectors.toSet());
    }
    @Synchronized
    public List<SalaryWorker> getAllActive(){
        return salaryWorkerRepository.findByStatusTrue();
    }
}