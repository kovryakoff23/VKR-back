package com.boot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "PaymentSupplier")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentSupplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;
    Date datePay;
    Date dateExecution;
    String unitName;
    String namePosition;
    boolean status;
    boolean statusExecution;
    Long sumPay;
    @ManyToOne(optional = true, cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "suppliers_id")
    Suppliers suppliers;
    @JsonIgnore
    @OneToOne(mappedBy = "paymentSupplier",cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    UnitDeliveriesPosition unitDeliveriesPosition;

    public PaymentSupplier(Date datePay, String unitName, String namePosition,
                           boolean status, Long sumPay, Suppliers suppliers) {
        this.datePay = datePay;
        this.unitName = unitName;
        this.namePosition = namePosition;
        this.status = status;
        this.sumPay = sumPay;
        this.suppliers = suppliers;
        this.unitDeliveriesPosition = new UnitDeliveriesPosition();
    }
}
