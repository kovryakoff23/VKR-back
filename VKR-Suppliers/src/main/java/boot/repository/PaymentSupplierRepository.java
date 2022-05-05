package boot.repository;

import boot.entity.PaymentSupplier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentSupplierRepository extends JpaRepository<PaymentSupplier, Long> {
    List<PaymentSupplier> findByStatusTrue();

}
