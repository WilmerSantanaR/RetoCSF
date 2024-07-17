package com.example.empresa.repository;

import com.example.empresa.model.PQRS;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PQRSRepository extends JpaRepository<PQRS, Long> {
    // Aquí puedes definir métodos adicionales según necesidades específicas
}
