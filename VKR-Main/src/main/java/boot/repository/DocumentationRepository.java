package boot.repository;

import boot.entity.Documentation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentationRepository extends JpaRepository<Documentation, Long> {
}
