package com.rizwan.order.Order;

import com.rizwan.order.Product.PurchaseRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.util.List;
@Validated
public record OrderRequest(
        Integer id,
        String reference,
        @Positive(message = "order amount should be positive")
        BigDecimal amount,
        @NotNull(message = "payment method should be precised ")
        PaymentMethod paymentMethod,
        @NotNull(message = "customer should be present ")
        @NotEmpty(message = "customer should be present ")
        @NotBlank(message = "customer should be present ")
        String customerId,
        @NotEmpty(message = "You should at least purchase one product ")
        List<PurchaseRequest> products
                           ) {
}
