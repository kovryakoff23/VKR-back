package boot.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
@Entity
@Table(name = "Suppliers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Suppliers {
    @Id
    @Column(name = "suppliers_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String numberAccount;
    private String bic;
    private String kpp;
    private String inn;
    private String address;
    private String contactPhoneNumber;
    private String email;
    @OneToMany(mappedBy="suppliers", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    private Set<SupplierPositions> supplyPositions = new HashSet<>();
    @OneToMany (mappedBy="suppliers", cascade = CascadeType.DETACH, fetch=FetchType.LAZY)
    private Set<PaymentSupplier> paymentSuppliers = new HashSet<>();


}
