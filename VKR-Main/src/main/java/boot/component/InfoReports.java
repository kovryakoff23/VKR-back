package boot.component;

import org.springframework.stereotype.Component;

@Component
public class InfoReports {
    Long sumSalary;
    Long sumPayment;
    Long countUnit;
    Long countUnitActive;
    Long quantityWorker;
    Long quantitySuppliers;

    public InfoReports(){}

    public InfoReports(Long sumSalary,
                       Long sumPayment, Long countUnit, Long countUnitActive, Long quantityWorker, Long quantitySuppliers) {
        this.sumSalary = sumSalary;
        this.sumPayment = sumPayment;
        this.countUnit = countUnit;
        this.countUnitActive = countUnitActive;
        this.quantityWorker = quantityWorker;
        this.quantitySuppliers = quantitySuppliers;
    }

    public Long getSumSalary() {
        return sumSalary;
    }

    public void setSumSalary(Long sumSalary) {
        this.sumSalary = sumSalary;
    }

    public Long getSumPayment() {
        return sumPayment;
    }

    public void setSumPayment(Long sumPayment) {
        this.sumPayment = sumPayment;
    }

    public Long getCountUnit() {
        return countUnit;
    }

    public void setCountUnit(Long countUnit) {
        this.countUnit = countUnit;
    }

    public Long getCountUnitActive() {
        return countUnitActive;
    }

    public void setCountUnitActive(Long countUnitActive) {
        this.countUnitActive = countUnitActive;
    }

    public Long getQuantityWorker() {
        return quantityWorker;
    }

    public void setQuantityWorker(Long quantityWorker) {
        this.quantityWorker = quantityWorker;
    }

    public Long getQuantitySuppliers() {
        return quantitySuppliers;
    }

    public void setQuantitySuppliers(Long quantitySuppliers) {
        this.quantitySuppliers = quantitySuppliers;
    }
}
