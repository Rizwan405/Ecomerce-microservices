package com.rizwan.ecomerce.Customer;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/customers")
public class CustomerController {
    private final CustomerService service;
    @PostMapping
    public ResponseEntity<String> createCustomer(@RequestBody @Valid customerRequest request){
        return ResponseEntity.ok(this.service.createCustomer(request));
    }
    @PutMapping
    public ResponseEntity<String> updateCustomer(@RequestBody @Valid customerRequest request){
        this.service.updateCustomer(request);
        return ResponseEntity.accepted().build();
    }
    @GetMapping
    public ResponseEntity<List<CustomerResponse>> findAll(){
        return ResponseEntity.ok(this.service.findAllCustomers());

    }
    @GetMapping("/exists/{customer-id}")
    public ResponseEntity<Boolean> exists(@PathVariable("customer-id") String customerId){
        return ResponseEntity.ok(this.service.customerExist(customerId));
    }
    @GetMapping("/{customer-id}")
    public ResponseEntity<CustomerResponse> findById(@PathVariable("customer-id") String customerId){
        return ResponseEntity.ok(this.service.findById(customerId));
    }

}
