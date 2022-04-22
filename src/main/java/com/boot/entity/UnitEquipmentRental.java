package com.boot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "UnitEquipmentRental")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UnitEquipmentRental {
    @Id
    @Column(name = "unit_equipment_rental_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String nameEquipment;
    Date dateUse;
    Long price;
    String note;
    @ManyToOne(optional=true, cascade=CascadeType.DETACH, fetch=FetchType.EAGER)
    @JoinColumn (name="unit_id")
    private Unit unit;

}
