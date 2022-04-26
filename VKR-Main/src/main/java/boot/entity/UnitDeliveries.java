package boot.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "UnitDeliveries")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UnitDeliveries {
    @Id
    @Column(name = "unit_deliveries_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String name;
    Date dateCreate;
    Date dateComplete;
    String description;
    boolean status;
    @ManyToOne(optional=false, cascade=CascadeType.DETACH, fetch=FetchType.EAGER)
    @JoinColumn (name="unit_id")
    private Unit unit;
    @OneToMany (mappedBy="unitDeliveries", fetch=FetchType.EAGER, orphanRemoval = true)
    private Set<UnitDeliveriesPosition> unitDeliveriesPositions = new HashSet<>();

}
