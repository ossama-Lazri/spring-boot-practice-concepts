package com.example.springboot_api_fundamentals.service;

import com.example.springboot_api_fundamentals.dto.ProductDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private List<ProductDTO> products = new ArrayList<>();

    public void save(ProductDTO product) {
        products.add(product);
    }

    public List<ProductDTO> getAll() {
        return products;
    }
}
