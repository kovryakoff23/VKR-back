package com.boot.service;

import com.boot.entity.UnitEquipmentRental;
import com.boot.entity.UnitUpkeep;
import com.boot.repository.UnitEquipmentRentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UnitEquipmentRentalService implements ServiceMag<UnitEquipmentRental>{
    @Autowired
    UnitEquipmentRentalRepository unitEquipmentRentalRepository;
    @Override
    public UnitEquipmentRental get(long id) {
        return unitEquipmentRentalRepository.findById(id).get();
    }

    @Override
    public List<UnitEquipmentRental> getAll() {
        return unitEquipmentRentalRepository.findAll();
    }

    @Override
    public void save(UnitEquipmentRental entity) {
        unitEquipmentRentalRepository.save(entity);
    }

    @Override
    public void delete(long id) {
        unitEquipmentRentalRepository.deleteById(id);
    }
}
