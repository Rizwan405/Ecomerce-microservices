package com.rizwan.ecomerce.payment;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Validated
public record Customer(
        String id,
        @NotNull(message = "FirstName cannot be null")
        String firstName,
        @NotNull(message = "LastName cannot be null")
        String lastName,
        @Email(message = "Email should be valid")
        @NotNull(message = "Email cannot be null")
        String email

) {
}
