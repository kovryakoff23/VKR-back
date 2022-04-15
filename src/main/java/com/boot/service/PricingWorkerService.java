package com.boot.service;

import com.boot.entity.PricingWorker;
import com.boot.entity.Worker;
import com.boot.repository.PricingWorkerRepository;
import com.boot.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PricingWorkerService implements ServiceMag<PricingWorker> {
    PricingWorkerRepository pricingWorkerRepository;

    @Autowired
    public PricingWorkerService(PricingWorkerRepository pricingWorkerRepository) {
        this.pricingWorkerRepository = pricingWorkerRepository;
    }

    @Override
    public PricingWorker get(long id) {
        return pricingWorkerRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public List<PricingWorker> getAll() {
        return pricingWorkerRepository.findAll();
    }

    @Override
    public void save(PricingWorker entity) {
        pricingWorkerRepository.save(entity);
    }

    @Override
    public void delete(long id) {
        pricingWorkerRepository.deleteById(id);
    }
}
