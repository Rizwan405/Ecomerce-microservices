package com.rizwan.ecomerce.Customer;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document
public class CustomerEntity {
    @Id
    private String id;
    private String fistName;
    private String lastName;
    private String email;
    private Address address;
}
