package com.rizwan.prdocut.Product;

import java.math.BigDecimal;

public record ProductResponse(
        Integer id,
        String name,
        String description,
        double avaliableQuantity,
        BigDecimal price,
        Integer categoryId,
        String categoryName,
        String categoryDescription

) {
}
