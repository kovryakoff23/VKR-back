package com.boot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "Worker")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Worker {
    @Id
    @Column(name = "worker_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String name;
    String phoneNumber;
    String numberAccount;
    String bic;
    String kpp;
    String inn;
    boolean status;
    @JsonIgnore
    @OneToMany (mappedBy="worker",  cascade = CascadeType.ALL, fetch=FetchType.LAZY)
    private Set<UnitProductionPosition> unitProductionPositions =new HashSet<>();
    @JsonIgnore
    @OneToMany (mappedBy="worker", cascade = CascadeType.DETACH, fetch=FetchType.LAZY)
    private Set<PricingWorker> pricingWorkers = new HashSet<>();
    @JsonIgnore
    @OneToMany (mappedBy="worker", cascade = CascadeType.DETACH, fetch=FetchType.EAGER)
    private Set<SalaryWorker> salaryWorkers = new HashSet<>();

}
