package com.example.springboot_api_fundamentals.controller;

import com.example.springboot_api_fundamentals.model.Product;
import com.example.springboot_api_fundamentals.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // curl http://localhost:8080/api/products
    @GetMapping
    public List<Product> getAll() {
        return productService.listAll();
    }

    // curl http://localhost:8080/api/products/1
    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable Long id) {
        return productService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // curl "http://localhost:8080/api/products/search?q=phone"
    @GetMapping("/search")
    public List<Product> search(@RequestParam String q) {
        return productService.search(q);
    }

    // curl -X POST http://localhost:8080/api/products -H "Content-Type: application/json" -d '{"id": 3, "name": "Headphones", "price": 199.9, "category": "Electronics"}'
    @PostMapping
    public ResponseEntity<String> create(@Valid @RequestBody Product product) {
        productService.add(product);
        return ResponseEntity.status(HttpStatus.CREATED).body("Product record created successfully.");
    }

    // curl -X PUT http://localhost:8080/api/products/1 -H "Content-Type: application/json" -d '{"name": "iPhone 15", "price": 999.0, "category": "Electronics"}'
    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @Valid @RequestBody Product product) {
        boolean updated = productService.update(id, product);
        return updated ? ResponseEntity.ok("Product information updated.") : ResponseEntity.notFound().build();
    }

    // curl -X DELETE http://localhost:8080/api/products/2
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        boolean removed = productService.remove(id);
        return removed ? ResponseEntity.ok("Product successfully removed.") : ResponseEntity.notFound().build();
    }
}
