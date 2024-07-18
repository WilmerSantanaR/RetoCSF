package com.example.empresa.services;

import com.example.empresa.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductCodeGeneratorService {

    @Autowired
    private ProductRepository productRepository;

    public String generateProductCode() {
        // Implementa la lógica para generar códigos únicos, por ejemplo, incrementando un contador
        long nextId = productRepository.count() + 1; // Cambiado a long
        return String.format("P%03d", nextId); // El formato %03d funciona con int, pero %03d puede usar long
    }
}
