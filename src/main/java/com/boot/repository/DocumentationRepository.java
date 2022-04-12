package com.boot.repository;

import com.boot.entity.Documentation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentationRepository extends JpaRepository<Documentation, Long> {
}
