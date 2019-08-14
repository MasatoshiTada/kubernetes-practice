package com.example.ui.service;

import com.example.ui.persistence.entity.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    List<Customer> findAll();

    Optional<Customer> findById(Integer id);

    void insert(Customer customer);
}
