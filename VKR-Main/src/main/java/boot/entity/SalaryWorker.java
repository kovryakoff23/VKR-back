package boot.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "SalaryWorker")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SalaryWorker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;
    Date dateSalary;
    Date dateExecution;
    String unitName;
    String namePosition;
    boolean statusExecution;
    boolean status;
    Long sumSalary;
    @ManyToOne(optional = true, cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "worker_id")
    Worker worker;
    @OneToOne(mappedBy = "salaryWorker",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    UnitProductionPosition unitProductionPosition;

    public SalaryWorker(Date dateSalary, Date dateExecution, String unitName, String namePosition,
                        boolean statusExecution, boolean status, Long sumSalary,
                        Worker worker, UnitProductionPosition unitProductionPosition) {
        this.dateSalary = dateSalary;
        this.dateExecution = dateExecution;
        this.unitName = unitName;
        this.namePosition = namePosition;
        this.statusExecution = statusExecution;
        this.status = status;
        this.sumSalary = sumSalary;
        this.worker = worker;
        this.unitProductionPosition = unitProductionPosition;
    }


}