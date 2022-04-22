package com.boot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "UnitUpkeep")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UnitUpkeep {
    @Id
    @Column(name = "unit_unitUpkeep_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String name;
    Long price;
    String note;
    @ManyToOne(optional=true, cascade=CascadeType.DETACH, fetch=FetchType.EAGER)
    @JoinColumn (name="unit_id")
    private Unit unit;

}
