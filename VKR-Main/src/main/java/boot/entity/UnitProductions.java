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
@Table(name = "UnitProductions")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UnitProductions {
    @Id
    @Column(name = "unit_production_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    Date dateStartWork;
    Date dateEndWork;
    String nameWork;
    String description;
    boolean status;
    @ManyToOne(optional=false, cascade=CascadeType.DETACH, fetch=FetchType.EAGER)
    @JoinColumn (name="unit_id")
    private Unit unit;
    @OneToMany (mappedBy="unitProductions", orphanRemoval = true)
    private Set<UnitProductionPosition> unitProductionPositions = new HashSet<>();

}
