package com.example.springboot_api_fundamentals.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class ProductDTO {

    @NotBlank(message = "Product name cannot be empty.")
    private String name;

    @Positive(message = "Price must be greater than zero.")
    private Double price;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }
}
