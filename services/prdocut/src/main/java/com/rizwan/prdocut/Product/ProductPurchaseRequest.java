package com.rizwan.prdocut.Product;

import jakarta.validation.constraints.NotNull;

public record ProductPurchaseRequest(
        @NotNull(message = "product is mendatory")
        Integer productId,
        @NotNull(message = "Quantity is mandatory")
        double quantity
) {
}
