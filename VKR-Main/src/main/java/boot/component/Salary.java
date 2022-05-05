package boot.component;

import boot.entity.Worker;
import org.springframework.stereotype.Component;

@Component
public class Salary {
    Worker worker;
    Long sumSalary;
    public Salary(){}
    public Salary(Worker worker, Long sumSalary) {
        this.worker = worker;
        this.sumSalary = sumSalary;
    }

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    public Long getSumSalary() {
        return sumSalary;
    }

    public void setSumSalary(Long sumSalary) {
        this.sumSalary = sumSalary;
    }
}
