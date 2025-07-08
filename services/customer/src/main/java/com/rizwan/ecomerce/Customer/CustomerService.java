package com.rizwan.ecomerce.Customer;


import com.rizwan.ecomerce.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.rizwan.ecomerce.exeption.CustomerNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerMapper mapper;
    private final CustomerRepository repository;
    public String createCustomer(customerRequest request) {
        if (request != null){
            CustomerEntity entity = mapper.toCustomerEntity(request);
            repository.save(entity);
            return "Customer created successfully";
        }
        return null;
    }

    public void updateCustomer(customerRequest request) {
        CustomerEntity customer = repository.findById(request.id()).orElseThrow(()->new com.rizwan.ecomerce.exeption.CustomerNotFoundException(String.format("Cannot update customer:: No customer found with the provided ID: %s", request.id())));
        mergeCustomer(customer, request);
        repository.save(customer);
    }
    public void mergeCustomer(CustomerEntity entity, customerRequest request){
        if(request.fistName() != null) entity.setFistName(request.fistName());
        if (request.lastName()!=null) entity.setLastName(request.lastName());
        if (request.email()!=null) entity.setEmail(request.email());
        if (request.address()!=null) entity.setAddress(request.address());

    }

    public List<CustomerResponse> findAllCustomers() {
//        fist they fetch list of CustomerEntity
//        with the help of mapper convert into response
        return repository.findAll().stream().map(mapper::fromCustomer).collect(Collectors.toList());

    }

    public Boolean customerExist(String customerId) {
        return repository.existsById(customerId);
    }

    public CustomerResponse findById(String customerId) {
        CustomerEntity customer = repository.findById(customerId).orElseThrow(()->new CustomerNotFoundException(String.format("Cannot find customer:: No customer found with the provided ID: %s", customerId)));
        return mapper.fromCustomer(customer);
    }
}
