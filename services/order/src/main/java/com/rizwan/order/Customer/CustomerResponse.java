package com.rizwan.order.Customer;

public record CustomerResponse(
        String id,
        String firstName,
        String lastName,
        String email
) {
}
