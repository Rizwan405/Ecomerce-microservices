package com.rizwan.prdocut.Product;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProductRequest(
        Integer id ,
        @NotNull(message = "Product name is required")
        String name,
        @NotNull(message = "Product description is required")
        String description,
        @Positive(message = "Available quantity should be positive ")
        double availableQuantity,
        @Positive(message = "Value must be positive")
        BigDecimal price,
        @NotNull(message = "price categoryId is required ")
        Integer CategoryId) {
}
