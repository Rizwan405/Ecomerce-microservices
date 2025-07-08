package com.rizwan.ecomerce.Customer;

import org.springframework.stereotype.Service;

@Service
public class CustomerMapper {
//    to customer
    public CustomerEntity toCustomerEntity(customerRequest request) {
        if (request == null) return null;
        return CustomerEntity.builder()
                .fistName(request.fistName())
                .lastName(request.lastName())
                .email(request.email())
                .address(request.address())
                .build();
    }
//    to customer response
    public CustomerResponse fromCustomer(CustomerEntity customer){
        if(customer == null){
            return null;
        }
        return new CustomerResponse(customer.getId(),customer.getFistName(),customer.getLastName(),customer.getEmail(),customer.getAddress());
    }

}
