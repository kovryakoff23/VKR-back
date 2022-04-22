package com.boot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "UnitDeliveriesPosition")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UnitDeliveriesPosition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;
    String namePosition;
    String measure;
    Long quantity;
    Long priceOne;
    Long price;
    boolean status;
    @ManyToOne(optional=true, cascade=CascadeType.DETACH, fetch=FetchType.EAGER)
    @JoinColumn (name="unit_deliveries_id")
    private UnitDeliveries unitDeliveries;
    @ManyToOne (optional = true, cascade=CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn (name="suppliers_id")
    Suppliers suppliers;
    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch=FetchType.EAGER)
    @JoinColumn(name = "paymentSupplier_id", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    PaymentSupplier paymentSupplier;

}
