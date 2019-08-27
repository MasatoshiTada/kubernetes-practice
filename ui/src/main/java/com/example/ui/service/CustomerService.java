package com.example.ui.service;

import com.example.ui.service.request.CustomerRequest;
import com.example.ui.service.response.CustomerResponse;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    List<CustomerResponse> findAll();

    Optional<CustomerResponse> findById(Integer id);

    void insert(CustomerRequest customer);
}
