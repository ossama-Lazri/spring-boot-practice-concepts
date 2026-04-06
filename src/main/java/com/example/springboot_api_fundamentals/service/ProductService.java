package com.example.springboot_api_fundamentals.service;

import com.example.springboot_api_fundamentals.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final List<Product> products = new ArrayList<>();

    public ProductService() {
        products.add(new Product(1L, "Smartphone", 799.0, "Electronics"));
        products.add(new Product(2L, "Desk Chair", 150.0, "Furniture"));
    }

    public List<Product> listAll() {
        return products;
    }

    public Optional<Product> findById(Long id) {
        return products.stream().filter(p -> p.getId().equals(id)).findFirst();
    }

    public void add(Product product) {
        products.add(product);
    }

    public void save(com.example.springboot_api_fundamentals.dto.ProductDTO dto) {
        products.add(new Product((long) (products.size() + 1), dto.getName(), dto.getPrice(), dto.getCategory()));
    }

    public boolean update(Long id, Product updated) {
        return findById(id).map(p -> {
            p.setName(updated.getName());
            p.setPrice(updated.getPrice());
            p.setCategory(updated.getCategory());
            return true;
        }).orElse(false);
    }

    public boolean remove(Long id) {
        return products.removeIf(p -> p.getId().equals(id));
    }

    public List<Product> search(String query) {
        return products.stream()
                .filter(p -> p.getName().toLowerCase().contains(query.toLowerCase()))
                .toList();
    }
}
