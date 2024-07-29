package com.example.empresa.services;

import com.example.empresa.model.Product;
import com.example.empresa.model.Category;
import com.example.empresa.repository.ProductRepository;
import com.example.empresa.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Product createProduct(String name, String description, Double price, int stock, Long categoryId, String imageUrl) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new RuntimeException("Category not found"));
        Product product = new Product(name, description, price, stock, category, imageUrl);
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, String name, String description, Double price, int stock, Long categoryId, String imageUrl) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new RuntimeException("Category not found"));

        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setStock(stock);
        product.setCategory(category);
        product.setImageUrl(imageUrl);  // Actualizar imageUrl

        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
