package com.rizwan.ecomerce.Customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record customerRequest(
        String id,
        @NotNull(message = "First name cannot be null")
        String fistName,
        @NotNull(message = "Last name cannot be null")
        String lastName,
        @NotNull(message = "Email cannot be null")
        @Email(message = "Email should be valid")
        String email,

        Address address) {

}
