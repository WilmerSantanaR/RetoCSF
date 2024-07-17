package com.example.empresa.services;

import com.example.empresa.model.PQRS;
import com.example.empresa.repository.PQRSRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PQRSService {

    @Autowired
    private PQRSRepository pqrsRepository;

    public PQRS createPQRS(String type, String description, Long userId) {
        PQRS pqrs = new PQRS(type, description, userId, "Pendiente", null);
        return pqrsRepository.save(pqrs);
    }

    public List<PQRS> getAllPQRS() {
        return pqrsRepository.findAll();
    }

    public Optional<PQRS> getPQRSById(Long id) {
        return pqrsRepository.findById(id);
    }

    public PQRS updatePQRSStatus(Long id, String status) {
        Optional<PQRS> pqrsOptional = pqrsRepository.findById(id);
        if (pqrsOptional.isPresent()) {
            PQRS pqrs = pqrsOptional.get();
            pqrs.setStatus(status);
            return pqrsRepository.save(pqrs); // Devuelve directamente el objeto PQRS actualizado
        }
        return null; // Manejar caso de PQRS no encontrada
    }

    public PQRS addCommentToPQRS(Long id, String comment) {
        Optional<PQRS> pqrsOptional = pqrsRepository.findById(id);
        if (pqrsOptional.isPresent()) {
            PQRS pqrs = pqrsOptional.get();
            pqrs.setComment(comment);
            return pqrsRepository.save(pqrs); // Devuelve directamente el objeto PQRS actualizado
        }
        return null; // Manejar caso de PQRS no encontrada
    }

    // Otros métodos según necesidades
}
