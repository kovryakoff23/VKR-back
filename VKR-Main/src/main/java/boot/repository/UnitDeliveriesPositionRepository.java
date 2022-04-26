package boot.repository;

import boot.entity.UnitDeliveriesPosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnitDeliveriesPositionRepository  extends JpaRepository<UnitDeliveriesPosition, Long> {
}
