package com.boot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "SupplyPositions")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SupplierPositions {
    @Id
    @Column(name = "suppliers_positions_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    String namePosition;
    String measure;
    Long priceOne;
    @ManyToOne(optional=true, cascade= CascadeType.DETACH, fetch= FetchType.EAGER)
    @JoinColumn(name="supply_id")
    private Suppliers suppliers;

}
