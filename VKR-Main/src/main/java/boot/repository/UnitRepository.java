package boot.repository;

import boot.entity.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UnitRepository extends JpaRepository<Unit, Long> {
    List<Unit> findByTypeTrue();

    @Query(value = "SELECT U.* FROM public.Unit U WHERE UPPER(U.name) LIKE UPPER(?1)", nativeQuery = true)
    List<Unit> findByName(String search);
}
