package com.rizwan.prdocut.Product;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.stereotype.Service;

@Service

public class ProductMapper {
    public Product toProduct(ProductRequest productRequest) {
        return Product.builder().
                id(productRequest.id())
                .availableQuantity(productRequest.availableQuantity())
                .name(productRequest.name())
                .description(productRequest.description())
                .price(productRequest.price())
                .category(
                        Category.builder().
                                id(productRequest.id())
                                .build()
                )
                .build();
    }

    public ProductResponse toProductResponse(Product product) {
       return new ProductResponse(
               product.getId(),
               product.getName(),
               product.getDescription(),
               product.getAvailableQuantity(),
               product.getPrice(),
               product.getCategory().getId(),
               product.getCategory().getName(),
               product.getCategory().getDescription()
       );
    }

    public ProductPurchaseResponse toProductPurchaseResponse(Product product, double quantity) {
        return new ProductPurchaseResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                quantity
        );
    }
}
