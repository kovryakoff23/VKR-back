package boot.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name = "PaymentSupplier")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentSupplier {
    @Id
    @Column(name = "id")
    Long id;
    Date datePay;
    Date dateExecution;
    String namePosition;
    boolean status;
    boolean statusExecution;
    Long sumPay;
    @ManyToOne(optional = true, cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "suppliers_id")
    Suppliers suppliers;

    public PaymentSupplier(Long id, Date datePay, String namePosition,
                           boolean status, Long sumPay, Suppliers suppliers) {
        this.id = id;
        this.datePay = datePay;
        this.namePosition = namePosition;
        this.status = status;
        this.sumPay = sumPay;
        this.suppliers = suppliers;
    }
}
