package com.boot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "SalaryWorker")
public class SalaryWorker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;
    Date dateSalary;
    Date dateExecution;
    String unitName;
    String namePosition;
    boolean statusExecution;
    boolean status;
    Long sumSalary;
    @ManyToOne(optional = true, cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "worker_id")
    Worker worker;
    @JsonIgnore
    @OneToOne(mappedBy = "salaryWorker", fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    UnitProductionPosition unitProductionPosition;

    public SalaryWorker(Date dateSalary, String unitName, String namePosition, boolean status,
                        Long sumSalary, Worker worker, UnitProductionPosition unitProductionPosition) {
        this.dateSalary = dateSalary;
        this.unitName = unitName;
        this.namePosition = namePosition;
        this.status = status;
        this.sumSalary = sumSalary;
        this.worker = worker;
        this.unitProductionPosition = unitProductionPosition;
    }

    public SalaryWorker(Date dateSalary, Date dateExecution, String unitName, String namePosition,
                        boolean statusExecution, boolean status, Long sumSalary,
                        Worker worker, UnitProductionPosition unitProductionPosition) {
        this.dateSalary = dateSalary;
        this.dateExecution = dateExecution;
        this.unitName = unitName;
        this.namePosition = namePosition;
        this.statusExecution = statusExecution;
        this.status = status;
        this.sumSalary = sumSalary;
        this.worker = worker;
        this.unitProductionPosition = unitProductionPosition;
    }

    public UnitProductionPosition getUnitProductionPosition() {
        return unitProductionPosition;
    }

    public boolean isStatusExecution() {
        return statusExecution;
    }

    public void setStatusExecution(boolean statusExecution) {
        this.statusExecution = statusExecution;
    }

    public void setUnitProductionPosition(UnitProductionPosition unitProductionPosition) {
        this.unitProductionPosition = unitProductionPosition;
    }

    public Date getDateExecution() {
        return dateExecution;
    }

    public void setDateExecution(Date dateExecution) {
        this.dateExecution = dateExecution;
    }

    public String getNamePosition() {
        return namePosition;
    }

    public SalaryWorker(Date dateSalary, String unitName, String namePosition, boolean status, Long sumSalary, Worker worker) {
        this.dateSalary = dateSalary;
        this.unitName = unitName;
        this.namePosition = namePosition;
        this.status = status;
        this.sumSalary = sumSalary;
        this.worker = worker;
    }

    public void setNamePosition(String namePosition) {
        this.namePosition = namePosition;
    }

    public SalaryWorker() {
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public SalaryWorker(Date dateSalary, String unitName, boolean status, Long sumSalary, Worker worker) {
        this.dateSalary = dateSalary;
        this.unitName = unitName;
        this.status = status;
        this.sumSalary = sumSalary;
        this.worker = worker;
    }

    public SalaryWorker(Long id, Date dateSalary, boolean status, Long sumSalary, Worker worker) {
        this.id = id;
        this.dateSalary = dateSalary;
        this.status = status;
        this.sumSalary = sumSalary;
        this.worker = worker;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateSalary() {
        return dateSalary;
    }

    public void setDateSalary(Date dateSalary) {
        this.dateSalary = dateSalary;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Long getSumSalary() {
        return sumSalary;
    }

    public void setSumSalary(Long sumSalary) {
        this.sumSalary = sumSalary;
    }

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }
}