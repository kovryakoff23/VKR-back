package boot.component;

import boot.entity.Suppliers;
import org.springframework.stereotype.Component;

@Component
public class Payment {
    Suppliers suppliers;
    Long sumPay;
    public Payment(){}
    public Payment(Suppliers suppliers, Long sumPay) {
        this.suppliers = suppliers;
        this.sumPay = sumPay;
    }

    public Suppliers getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(Suppliers suppliers) {
        this.suppliers = suppliers;
    }

    public Long getSumPay() {
        return sumPay;
    }

    public void setSumPay(Long sumPay) {
        this.sumPay = sumPay;
    }
}
