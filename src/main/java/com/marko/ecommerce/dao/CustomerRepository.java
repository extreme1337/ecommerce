package com.marko.ecommerce.dao;

import com.marko.ecommerce.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
