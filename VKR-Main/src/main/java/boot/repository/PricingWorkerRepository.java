package boot.repository;

import boot.entity.PricingWorker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PricingWorkerRepository extends JpaRepository<PricingWorker, Long> {
}
