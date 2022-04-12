package com.boot.service;

import com.boot.entity.Documentation;
import com.boot.entity.PricingWorker;
import com.boot.repository.DocumentationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DocumentationService implements ServiceMag<Documentation> {
    @Autowired
    DocumentationRepository documentationRepository;

    @Override
    public Documentation get(long id) {
        return documentationRepository.findById(id).get();
    }

    @Override
    public List<Documentation> getAll() {
        return documentationRepository.findAll();
    }

    @Override
    public void save(Documentation entity) {
        documentationRepository.save(entity);
    }

    @Override
    public void delete(long id) {
        documentationRepository.deleteById(id);
    }
}
