package boot.controller;

import boot.DTO.*;
import boot.component.Reports;
import boot.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping()
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

    @GetMapping("/units/")
    @PreAuthorize("hasRole('USER')")
    public List<UnitDTO> getUnit() {
        return  unitService.getAll();

    }
    @GetMapping("/units/search/{search}")
    public List<UnitDTO> getUnit(@PathVariable("search") String search) {
        return unitService.getAllSearch(search);

    }
    @PostMapping("/units/")
    public void saveUnit(@RequestBody UnitDTO unit) {
        try {
           unitService.save(unit);
       }catch(Exception e){
            System.out.println("error input");
       }
    }
    @DeleteMapping("/units/{unitId}")
    public void deleteUnit(@PathVariable("unitId") Long unitId) {
        try {
            unitService.delete(unitId);
        }catch(Exception e){
            System.out.println("error input");
        }
    }
    @GetMapping("/units/{unitId}")
    public UnitDTO getProductionUnit(@PathVariable("unitId") Long unitId) {
        return unitService.get(unitId);
    }


    @GetMapping("/units/deliveries/{unitId}")
    public Set<UnitDeliveriesDTO> getDeliveries(@PathVariable("unitId") Long unitId) {
        UnitDTO unitDTO = unitService.get(unitId);
        unitDTO.getUnitDeliveriesDTO().forEach(value->{
            value.setUnitDTO(unitDTO);
        });
        return unitDTO.getUnitDeliveriesDTO();
    }
    @PostMapping("/units/deliveries")
    public void saveDeliveries(@RequestBody UnitDeliveriesDTO unitDeliveries) {
        try {
            unitDeliveriesService.save(unitDeliveries);
        }catch(Exception e){
            System.out.println("error input");
        }
    }
    @PutMapping("/units/deliveries")
    public void updateDeliveries(@RequestBody UnitDeliveriesDTO unitDeliveries) {
        try {
            unitDeliveriesService.save(unitDeliveries);
        }catch(Exception e){
            System.out.println("error input");
        }
    }
    @GetMapping("/units/deliver/{deliverId}")
    public Set<UnitDeliveriesPositionDTO> getDeliveriesPositions(@PathVariable("deliverId") Long deliverId) {

        return unitDeliveriesService.get(deliverId).getUnitDeliveriesPositionsDTO();
    }

    @PostMapping("/units/deliver")
    public void setDeliveriesPositions(@RequestBody UnitDeliveriesPositionDTO unitDeliveriesPosition) {
        try {
            unitDeliveriesPositionService.save(unitDeliveriesPosition);
        }catch(Exception e){
            System.out.println("error input");
        }
    }
    @PutMapping ("/units/deliver")
    public void updateDeliveriesPositions(@RequestBody UnitDeliveriesPositionDTO unitDeliveriesPosition) {
        try {
            unitDeliveriesPositionService.update(unitDeliveriesPosition);
        }catch(Exception e){
            System.out.println("error input");
        }
    }
    @DeleteMapping("/units/deliver/{deliverPositionId}")
    public void deleteDeliveriesPositions(@PathVariable("deliverPositionId") Long deliverPositionId) {
        try {
            unitDeliveriesPositionService.delete(deliverPositionId);
        }catch(Exception e){
            System.out.println("error input");
        }
    }

    @GetMapping("/units/production/{unitId}")
    public Set<UnitProductionsDTO> getProductions(@PathVariable("unitId") Long unitId) {
        UnitDTO unitDTO = unitService.get(unitId);
        unitDTO.getUnitProductionsDTO().forEach(value->{
            value.setUnitDTO(unitDTO);
        });
        return unitDTO.getUnitProductionsDTO();
    }

    @PostMapping("/units/production")
    public void saveProduction(@RequestBody UnitProductionsDTO unitProductions) {
        try {
            unitProductionService.save(unitProductions);
       }catch(Exception e){
           throw new IllegalArgumentException("/units/production");
        }
    }

    @GetMapping("/units/productionPosition/{productionId}")
    public Set<UnitProductionPositionDTO> getUnitProductionPosition(@PathVariable("productionId") Long productionId) {
        return unitProductionService.get(productionId).getUnitProductionPositionsDTO();
    }

    @PostMapping("/units/productionPosition")
    public void setUnitProductionPosition(@RequestBody UnitProductionPositionDTO unitProductionPosition) {
        try {
            unitProductionPositionService.save(unitProductionPosition);
        }catch(Exception e){
            throw new IllegalArgumentException("/units/productionPosition");
        }
    }
    @PutMapping("/units/productionPosition")
    public void updateUnitProductionPosition(@RequestBody UnitProductionPositionDTO unitProductionPosition) {
        try {
            unitProductionPositionService.update(unitProductionPosition);
        }catch(Exception e){
            System.out.println("error input");
        }
    }

    @DeleteMapping("/units/productionPosition/{productionId}")
    public void deleteUnitProductionPosition(@PathVariable("productionId") Long productionId) {
        try {
            unitProductionPositionService.delete(productionId);
        }catch(Exception e){
            System.out.println("error input");
        }
    }
    @GetMapping("/units/productionPosition/byUnitId/{unitId}")
    public Set<WorkerDTO> getUnitProductionPositionWorkerByUnitId(@PathVariable("unitId") Long unitId) {
        return unitProductionPositionService.getWorker(unitId);
    }
    @GetMapping("/units/reports/{unitId}")
    public Reports getReport(@PathVariable("unitId") Long unitId) {
        return reportsService.getReport(unitId);
    }

    @GetMapping("/units/upkeep/{unitId}")
    public Set<UnitUpkeepDTO> getUnitUpkeep(@PathVariable("unitId") Long unitId) {
        return unitService.get(unitId).getUnitUpkeepsDTO();
    }

    @PostMapping("/units/upkeep")
    public void setUnitUpkeep(@RequestBody UnitUpkeepDTO unitUpkeep) {
        try {
            unitUpkeepService.save(unitUpkeep);
        }catch(Exception e){
            System.out.println("error input");
        }
    }

    @DeleteMapping("/units/upkeep/{upkeepId}")
    public void deleteUnitUpkeep(@PathVariable("upkeepId") Long upkeepId) {
        try {
            unitUpkeepService.delete(upkeepId);
        }catch(Exception e){
            System.out.println("error input");
        }
    }

    @GetMapping("/units/unitEquipmentRental/{unitId}")
    public Set<UnitEquipmentRentalDTO> getUnitEquipmentRental(@PathVariable("unitId") Long unitId) {
        return unitService.get(unitId).getUnitEquipmentRentalsDTO();
    }

    @PostMapping("/units/unitEquipmentRental")
    public void setUnitEquipmentRental(@RequestBody UnitEquipmentRentalDTO unitEquipmentRental) {
        try {
            unitEquipmentRentalService.save(unitEquipmentRental);
        }catch(Exception e){
            System.out.println("error input");
        }
    }

    @DeleteMapping("/units/unitEquipmentRental/{unitEquipmentRentalId}")
    public void deleteUnitEquipmentRental(@PathVariable("unitEquipmentRentalId") Long unitEquipmentRentalId) {
        try {
            unitEquipmentRentalService.delete(unitEquipmentRentalId);
        }catch(Exception e){
            System.out.println("error input");
        }
    }
}


