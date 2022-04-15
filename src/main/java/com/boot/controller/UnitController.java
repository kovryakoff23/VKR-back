package com.boot.controller;

import com.boot.component.Reports;
import com.boot.entity.*;
import com.boot.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UnitController {
    UnitService unitService;
    UnitProductionService unitProductionService;
    UnitDeliveriesService unitDeliveriesService;
    UnitDeliveriesPositionService unitDeliveriesPositionService;
    UnitProductionPositionService unitProductionPositionService;
    DocumentationService documentationService;
    ReportsService reportsService;
    UnitUpkeepService unitUpkeepService;
    UnitEquipmentRentalService unitEquipmentRentalService;

    @Autowired
    public UnitController(UnitService unitService, UnitProductionService unitProductionService, UnitDeliveriesService unitDeliveriesService, UnitDeliveriesPositionService unitDeliveriesPositionService, UnitProductionPositionService unitProductionPositionService, DocumentationService documentationService, ReportsService reportsService, UnitUpkeepService unitUpkeepService, UnitEquipmentRentalService unitEquipmentRentalService) {
        this.unitService = unitService;
        this.unitProductionService = unitProductionService;
        this.unitDeliveriesService = unitDeliveriesService;
        this.unitDeliveriesPositionService = unitDeliveriesPositionService;
        this.unitProductionPositionService = unitProductionPositionService;
        this.documentationService = documentationService;
        this.reportsService = reportsService;
        this.unitUpkeepService = unitUpkeepService;
        this.unitEquipmentRentalService = unitEquipmentRentalService;
    }

    @GetMapping("/units")
    public List<Unit> getUnit() {
        List<Unit> unitList =  unitService.getAll();
        return  (List<Unit>) unitService.getAll();

    }
    @GetMapping("/units/search/{search}")
    public List<Unit> getUnit(@PathVariable("search") String search) {
        List<Unit> unitListSearch =  new ArrayList<>();
        unitListSearch = unitService.getAllSearch(search);
        return  unitListSearch;

    }
    @PostMapping("/units")
    public void saveUnit(@RequestBody Unit unit) {
        try {
           unitService.save(unit);
       }catch(Exception e){
            System.out.println("error input");
       }
    }
    @DeleteMapping("units/{unitId}")
    public void deleteUnit(@PathVariable("unitId") Long unitId) {
        try {
            unitService.delete(unitId);
        }catch(Exception e){
            System.out.println("error input");
        }
    }
    @GetMapping("/units/{unitId}")
    public Unit getProductionUnit(@PathVariable("unitId") Long unitId) {
        return unitService.get(unitId);
    }


    @GetMapping("units/deliveries/{unitId}")
    public Set<UnitDeliveries> getDeliveries(@PathVariable("unitId") Long unitId) {
        return unitService.get(unitId).getUnitDeliveries();
    }
    @PostMapping("/units/deliveries")
    public void saveDeliveries(@RequestBody UnitDeliveries unitDeliveries) {
        try {
            unitDeliveriesService.save(unitDeliveries);
        }catch(Exception e){
            System.out.println("error input");
        }
    }
    @PutMapping("/units/deliveries")
    public void updateDeliveries(@RequestBody UnitDeliveries unitDeliveries) {
        try {
            unitDeliveriesService.save(unitDeliveries);
        }catch(Exception e){
            System.out.println("error input");
        }
    }
    @GetMapping("units/deliver/{deliverId}")
    public Set<UnitDeliveriesPosition> getDeliveriesPositions(@PathVariable("deliverId") Long deliverId) {
        Set<UnitDeliveriesPosition> unitDeliveriesPositions = unitDeliveriesService.get(deliverId).getUnitDeliveriesPositions();
        return unitDeliveriesPositions;
    }

    @PostMapping("/units/deliver")
    public void setDeliveriesPositions(@RequestBody UnitDeliveriesPosition unitDeliveriesPosition) {
        try {
            unitDeliveriesPositionService.save(unitDeliveriesPosition);
        }catch(Exception e){
            System.out.println("error input");
        }
    }
    @PutMapping ("/units/deliver")
    public void updateDeliveriesPositions(@RequestBody UnitDeliveriesPosition unitDeliveriesPosition) {
        try {
            unitDeliveriesPositionService.update(unitDeliveriesPosition);
        }catch(Exception e){
            System.out.println("error input");
        }
    }
    @DeleteMapping("units/deliver/{deliverPositionId}")
    public void deleteDeliveriesPositions(@PathVariable("deliverPositionId") Long deliverPositionId) {
        try {
            unitDeliveriesPositionService.delete(deliverPositionId);
        }catch(Exception e){
            System.out.println("error input");
        }
    }

    @GetMapping("units/production/{unitId}")
    public List<UnitProductions> getProductions(@PathVariable("unitId") Long unitId) {
        return unitService.get(unitId).getUnitProductionWorks();
    }

    @PostMapping("/units/production")
    public void saveProduction(@RequestBody UnitProductions unitProductions) {
        try {
            unitProductionService.save(unitProductions);
       }catch(Exception e){
           System.out.println("error input");
        }
    }

    @GetMapping("units/productionPosition/{productionId}")
    public Set<UnitProductionPosition> getUnitProductionPosition(@PathVariable("productionId") Long productionId) {
        return unitProductionService.get(productionId).getUnitProductionPositions();
    }

    @PostMapping("/units/productionPosition")
    public void setUnitProductionPosition(@RequestBody UnitProductionPosition unitProductionPosition) {
        try {
            unitProductionPositionService.save(unitProductionPosition);
        }catch(Exception e){
            System.out.println("error input");
        }
    }
    @PutMapping("/units/productionPosition")
    public void updateUnitProductionPosition(@RequestBody UnitProductionPosition unitProductionPosition) {
        try {
            unitProductionPositionService.update(unitProductionPosition);
        }catch(Exception e){
            System.out.println("error input");
        }
    }

    @DeleteMapping("units/productionPosition/{productionId}")
    public void deleteUnitProductionPosition(@PathVariable("productionId") Long productionId) {
        try {
            unitProductionPositionService.delete(productionId);
        }catch(Exception e){
            System.out.println("error input");
        }
    }
    @GetMapping("units/productionPosition/byUnitId/{unitId}")
    public Set<Worker> getUnitProductionPositionWorkerByUnitId(@PathVariable("unitId") Long unitId) {
        return unitProductionPositionService.getWorker(unitId);
    }
    @GetMapping("/units/reports/{unitId}")
    public Reports getReport(@PathVariable("unitId") Long unitId) {
        return reportsService.getReport(unitId);
    }

    @GetMapping("units/upkeep/{unitId}")
    public Set<UnitUpkeep> getUnitUpkeep(@PathVariable("unitId") Long unitId) {
        return unitService.get(unitId).getUnitUpkeeps();
    }

    @PostMapping("/units/upkeep")
    public void setUnitUpkeep(@RequestBody UnitUpkeep unitUpkeep) {
        try {
            unitUpkeepService.save(unitUpkeep);
        }catch(Exception e){
            System.out.println("error input");
        }
    }

    @DeleteMapping("units/upkeep/{upkeepId}")
    public void deleteUnitUpkeep(@PathVariable("upkeepId") Long upkeepId) {
        try {
            unitUpkeepService.delete(upkeepId);
        }catch(Exception e){
            System.out.println("error input");
        }
    }

    @GetMapping("units/unitEquipmentRental/{unitId}")
    public Set<UnitEquipmentRental> getUnitEquipmentRental(@PathVariable("unitId") Long unitId) {
        return unitService.get(unitId).getUnitEquipmentRentals();
    }

    @PostMapping("/units/unitEquipmentRental")
    public void setUnitEquipmentRental(@RequestBody UnitEquipmentRental unitEquipmentRental) {
        try {
            unitEquipmentRentalService.save(unitEquipmentRental);
        }catch(Exception e){
            System.out.println("error input");
        }
    }

    @DeleteMapping("units/unitEquipmentRental/{unitEquipmentRentalId}")
    public void deleteUnitEquipmentRental(@PathVariable("unitEquipmentRentalId") Long unitEquipmentRentalId) {
        try {
            unitEquipmentRentalService.delete(unitEquipmentRentalId);
        }catch(Exception e){
            System.out.println("error input");
        }
    }
}


