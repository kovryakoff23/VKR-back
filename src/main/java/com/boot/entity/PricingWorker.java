package com.boot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Entity
@Table(name = "PricingWorker")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PricingWorker {
    @Id
    @Column(name = "pricing_worker_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String namePosition;
    String measure;
    Long priceOne;
    String typeWork;
    @ManyToOne(optional=true, cascade=CascadeType.DETACH, fetch=FetchType.EAGER)
    @JoinColumn (name="worker_id")
    Worker worker;

}
