package boot.repository;

import boot.entity.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkerRepository extends JpaRepository<Worker, Long> {
    @Query(value = "SELECT W.* FROM Worker W WHERE UPPER(W.name) LIKE UPPER(?1)", nativeQuery = true)
    List<Worker> findByName(String search);
}
