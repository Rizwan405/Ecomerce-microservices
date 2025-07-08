package com.rizwan.ecomerce.Customer;

import jakarta.validation.constraints.NotNull;

public record CustomerResponse(String id,
                               String fistName,
                               String lastName,
                               String email,
                               Address address) {
}
