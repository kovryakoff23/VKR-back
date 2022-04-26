package boot.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Worker")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Worker {
    @Id
    @Column(name = "worker_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String name;
    String phoneNumber;
    String numberAccount;
    String bic;
    String kpp;
    String inn;
    boolean status;

    @OneToMany (mappedBy="worker",  cascade = CascadeType.ALL, fetch=FetchType.LAZY)
    private Set<UnitProductionPosition> unitProductionPositions =new HashSet<>();

    @OneToMany (mappedBy="worker", cascade = CascadeType.DETACH, fetch=FetchType.LAZY)
    private Set<PricingWorker> pricingWorkers = new HashSet<>();

    @OneToMany (mappedBy="worker", cascade = CascadeType.DETACH, fetch=FetchType.EAGER)
    private Set<SalaryWorker> salaryWorkers = new HashSet<>();

}
