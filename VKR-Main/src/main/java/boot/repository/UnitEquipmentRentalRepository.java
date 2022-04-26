package boot.repository;


import boot.entity.UnitEquipmentRental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnitEquipmentRentalRepository extends JpaRepository<UnitEquipmentRental, Long> {
}
