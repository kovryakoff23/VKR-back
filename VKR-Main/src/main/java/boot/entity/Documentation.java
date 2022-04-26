package boot.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "Documentation")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Documentation {
    @Id
    @Column(name = "pricing_worker_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String name;
    @ManyToOne(optional=false, cascade=CascadeType.DETACH, fetch=FetchType.EAGER)
    @JoinColumn (name="unit_id")
    private Unit unit;

    public Documentation(String name, Unit unit) {
        this.name = name;
        this.unit = unit;
    }
}
