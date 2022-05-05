package boot.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "unit_production_position")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UnitProductionPosition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;
    String namePosition;
    Long priceOne;
    Long quantity;
    Long price;
    String measure;
    boolean status;
    @ManyToOne(optional=true, cascade=CascadeType.DETACH, fetch=FetchType.EAGER)
    private UnitProductions unitProductions;
    @ManyToOne(optional=true, cascade=CascadeType.DETACH, fetch=FetchType.EAGER)
    @JoinColumn (name="worker_id")
    Worker worker;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch=FetchType.EAGER)
    @JoinColumn(name = "salaryWorker_id", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    SalaryWorker salaryWorker;

}
