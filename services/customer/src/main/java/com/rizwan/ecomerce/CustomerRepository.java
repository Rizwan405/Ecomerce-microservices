package com.rizwan.ecomerce;

import com.rizwan.ecomerce.Customer.CustomerEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<CustomerEntity, String>{
}
