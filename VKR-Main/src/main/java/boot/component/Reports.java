package boot.component;

import org.springframework.stereotype.Component;

@Component
public class Reports {
    int workReadiness;
    int supplyReadiness;
    Long sumSalaries;
    Long sumSupply;
    Long sumUpkeep;
    Long quantityWork;
    Long sumEquipmentRental;
    Long quantitySuccessfulWork;
    Long quantitySupplierPositions;
    Long quantitySuccessfulSupplierPositions;
    public Reports(){}

    public Long getSumUpkeep() {
        return sumUpkeep;
    }

    public void setSumUpkeep(Long sumUpkeep) {
        this.sumUpkeep = sumUpkeep;
    }

    public Long getSumEquipmentRental() {
        return sumEquipmentRental;
    }

    public void setSumEquipmentRental(Long sumEquipmentRental) {
        this.sumEquipmentRental = sumEquipmentRental;
    }

    public Reports(int workReadiness, int supplyReadiness, Long sumSalaries, Long sumSupply, Long sumUpkeep, Long quantityWork, Long sumEquipmentRental,
                   Long quantitySuccessfulWork, Long quantitySupplierPositions, Long quantitySuccessfulSupplierPositions) {
        this.workReadiness = workReadiness;
        this.supplyReadiness = supplyReadiness;
        this.sumSalaries = sumSalaries;
        this.sumSupply = sumSupply;
        this.sumUpkeep = sumUpkeep;
        this.quantityWork = quantityWork;
        this.sumEquipmentRental = sumEquipmentRental;
        this.quantitySuccessfulWork = quantitySuccessfulWork;
        this.quantitySupplierPositions = quantitySupplierPositions;
        this.quantitySuccessfulSupplierPositions = quantitySuccessfulSupplierPositions;
    }

    public int getWorkReadiness() {
        return workReadiness;
    }

    public void setWorkReadiness(int workReadiness) {
        this.workReadiness = workReadiness;
    }

    public int getSupplyReadiness() {
        return supplyReadiness;
    }

    public void setSupplyReadiness(int supplyReadiness) {
        this.supplyReadiness = supplyReadiness;
    }

    public Long getSumSalaries() {
        return sumSalaries;
    }

    public void setSumSalaries(Long sumSalaries) {
        this.sumSalaries = sumSalaries;
    }

    public Long getSumSupply() {
        return sumSupply;
    }

    public void setSumSupply(Long sumSupply) {
        this.sumSupply = sumSupply;
    }

    public Long getSumExpenses() {
        return sumUpkeep;
    }

    public void setSumExpenses(Long sumExpenses) {
        this.sumUpkeep = sumExpenses;
    }

    public Long getQuantityWork() {
        return quantityWork;
    }

    public void setQuantityWork(Long quantityWork) {
        this.quantityWork = quantityWork;
    }

    public Long getQuantitySuccessfulWork() {
        return quantitySuccessfulWork;
    }

    public void setQuantitySuccessfulWork(Long quantitySuccessfulWork) {
        this.quantitySuccessfulWork = quantitySuccessfulWork;
    }

    public Long getQuantitySupplierPositions() {
        return quantitySupplierPositions;
    }

    public void setQuantitySupplierPositions(Long quantitySupplierPositions) {
        this.quantitySupplierPositions = quantitySupplierPositions;
    }

    public Long getQuantitySuccessfulSupplierPositions() {
        return quantitySuccessfulSupplierPositions;
    }

    public void setQuantitySuccessfulSupplierPositions(Long quantitySuccessfulSupplierPositions) {
        this.quantitySuccessfulSupplierPositions = quantitySuccessfulSupplierPositions;
    }
}
