package boot.repository;

import boot.entity.UnitDeliveries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UnitDeliveriesRepository  extends JpaRepository<UnitDeliveries, Long> {
    List<UnitDeliveries> findByStatusTrue();
}
