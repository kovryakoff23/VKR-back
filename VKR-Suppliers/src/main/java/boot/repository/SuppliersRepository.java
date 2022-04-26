package boot.repository;

import boot.entity.Suppliers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SuppliersRepository  extends JpaRepository<Suppliers, Long> {
    @Query(value = "SELECT S.* FROM Suppliers S WHERE UPPER(S.name) LIKE UPPER(?1)", nativeQuery = true)
    List<Suppliers> findByName(String search);
}
