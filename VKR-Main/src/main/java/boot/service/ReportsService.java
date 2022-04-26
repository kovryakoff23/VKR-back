package boot.service;

import boot.DTO.*;
import boot.component.Reports;
import boot.entity.Unit;
import boot.entity.UnitEquipmentRental;
import boot.entity.UnitUpkeep;
import boot.mapstruct.UnitDeliveriesMapper;
import boot.mapstruct.UnitMapper;
import boot.mapstruct.UnitProductionsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportsService {

    UnitService unitService;

    UnitMapper unitMapper;

    UnitDeliveriesMapper unitDeliveriesMapper;

    UnitProductionsMapper unitProductionsMapper;
    @Autowired
    public ReportsService(UnitService unitService, UnitMapper unitMapper, UnitDeliveriesMapper unitDeliveriesMapper, UnitProductionsMapper unitProductionsMapper) {
        this.unitService = unitService;
        this.unitMapper = unitMapper;
        this.unitDeliveriesMapper = unitDeliveriesMapper;
        this.unitProductionsMapper = unitProductionsMapper;
    }

    public Reports getReport(Long unitId){
        UnitDTO unitDTO = unitService.get(unitId);
        Unit unit = unitMapper.unitDTOToUnit(unitDTO);
        Long sumSalaries = unitDTO.getUnitProductionsDTO().stream()
                .map(UnitProductionsDTO::getUnitProductionPositionsDTO)
                .map(var->(var.stream().map(UnitProductionPositionDTO::getPrice).mapToLong(Long::longValue).sum()))
                .mapToLong(Long::longValue)
                .sum();
        Long sumSupply = unitDTO.getUnitDeliveriesDTO().stream()
                .map(UnitDeliveriesDTO::getUnitDeliveriesPositionsDTO)
                .map(var->(var.stream().map(UnitDeliveriesPositionDTO::getPrice).mapToLong(Long::longValue).sum()))
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
        Long quantityWork = unitDTO.getUnitProductionsDTO().stream()
                .map(UnitProductionsDTO::getUnitProductionPositionsDTO)
                .count();
        Long quantitySuccessfulWork = unitDTO.getUnitProductionsDTO().stream()
                .filter(UnitProductionsDTO::isStatus)
                .map(UnitProductionsDTO::getUnitProductionPositionsDTO)
                .count();
        Long quantitySupplierPositions = unitDTO.getUnitDeliveriesDTO().stream()
                .map(UnitDeliveriesDTO::getUnitDeliveriesPositionsDTO)
                .count();
        Long quantitySuccessfulSupplierPositions = unitDTO.getUnitDeliveriesDTO().stream()
                .filter(UnitDeliveriesDTO::isStatus)
                .map(UnitDeliveriesDTO::getUnitDeliveriesPositionsDTO)
                .count();
        int workReadiness =(int)((quantitySuccessfulWork.doubleValue()/quantityWork.doubleValue())*100);
        int supplyReadiness = (int)((quantitySuccessfulSupplierPositions.doubleValue()/quantitySupplierPositions.doubleValue())*100);
        return new Reports(workReadiness, supplyReadiness, sumSalaries, sumSupply, sumUpkeep, quantityWork, sumEquipmentRental,
                 quantitySuccessfulWork, quantitySupplierPositions, quantitySuccessfulSupplierPositions);
    }
}
