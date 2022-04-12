package com.boot.service;

import com.boot.entity.SupplierPositions;
import com.boot.repository.SuppliersPositionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierPositionsService implements ServiceMag<SupplierPositions>{
    @Autowired
    SuppliersPositionsRepository supplierPositionsRepository;
    @Override
    public SupplierPositions get(long id) {
        return supplierPositionsRepository.findById(id).get();
    }

    @Override
    public List<SupplierPositions> getAll() {
        return supplierPositionsRepository.findAll();
    }

    @Override
    public void save(SupplierPositions entity) {
        supplierPositionsRepository.save(entity);
    }

    @Override
    public void delete(long id) {
        supplierPositionsRepository.deleteById(id);
    }
}
