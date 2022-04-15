package com.boot.service;

import com.boot.component.Reports;
import com.boot.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportsService {

    UnitService unitService;

    @Autowired
    public ReportsService(UnitService unitService) {
        this.unitService = unitService;
    }

    public Reports getReport(Long unitId){
        Unit unit = unitService.get(unitId);
        Long sumSalaries = unit.getUnitProductionWorks().stream()
                .map(UnitProductions::getUnitProductionPositions)
                .map(var->(var.stream().map(UnitProductionPosition::getPrice).mapToLong(Long::longValue).sum()))
                .mapToLong(Long::longValue)
                .sum();
        Long sumSupply = unit.getUnitDeliveries().stream()
                .map(UnitDeliveries::getUnitDeliveriesPositions)
                .map(var->(var.stream().map(UnitDeliveriesPosition::getPrice).mapToLong(Long::longValue).sum()))
                .mapToLong(Long::longValue)
                .sum();
        Long sumUpkeep = unit.getUnitUpkeeps().stream()
                .map(UnitUpkeep::getPrice)
                .mapToLong(Long::longValue)
                .sum();
        Long sumEquipmentRental  = unit.getUnitEquipmentRentals().stream()
                .map(UnitEquipmentRental::getPrice)
                .mapToLong(Long::longValue)
                .sum();
        Long quantityWork = unit.getUnitProductionWorks().stream()
                .map(UnitProductions::getUnitProductionPositions)
                .count();
        Long quantitySuccessfulWork = unit.getUnitProductionWorks().stream()
                .filter(UnitProductions::isStatus)
                .map(UnitProductions::getUnitProductionPositions)
                .count();
        Long quantitySupplierPositions = unit.getUnitDeliveries().stream()
                .map(UnitDeliveries::getUnitDeliveriesPositions)
                .count();
        Long quantitySuccessfulSupplierPositions = unit.getUnitDeliveries().stream()
                .filter(UnitDeliveries::isStatus)
                .map(UnitDeliveries::getUnitDeliveriesPositions)
                .count();
        int workReadiness =(int)((quantitySuccessfulWork.doubleValue()/quantityWork.doubleValue())*100);
        int supplyReadiness = (int)((quantitySuccessfulSupplierPositions.doubleValue()/quantitySupplierPositions.doubleValue())*100);
        return new Reports(workReadiness, supplyReadiness, sumSalaries, sumSupply, sumUpkeep, quantityWork, sumEquipmentRental,
                 quantitySuccessfulWork, quantitySupplierPositions, quantitySuccessfulSupplierPositions);
    }
}
