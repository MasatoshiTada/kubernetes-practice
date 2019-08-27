package com.example.api.service.impl;

import com.example.api.persistence.entity.Customer;
import com.example.api.persistence.mapper.CustomerMapper;
import com.example.api.service.CustomerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerMapper customerMapper) {
        this.customerMapper = customerMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Customer> findAll() {
        return customerMapper.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Customer> findById(Integer id) {
        return customerMapper.findById(id);
    }

    @Override
    @Transactional(readOnly = false)
    public void insert(Customer customer) {
        customerMapper.insert(customer);
    }
}
