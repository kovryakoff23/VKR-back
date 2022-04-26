package boot.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "UnitDeliveriesPosition")
@Getter
@Setter
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
    String nameSupplier;


}
