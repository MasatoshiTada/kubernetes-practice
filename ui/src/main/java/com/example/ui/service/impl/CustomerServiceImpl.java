package com.example.ui.service.impl;

import com.example.ui.ApiProperties;
import com.example.ui.service.CustomerService;
import com.example.ui.service.request.CustomerRequest;
import com.example.ui.service.response.CustomerResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

    private final RestTemplate restTemplate;
    private final String apiUrl;

    public CustomerServiceImpl(RestTemplate restTemplate, ApiProperties apiProperties) {
        this.restTemplate = restTemplate;
        this.apiUrl = String.format("http://%s:%d/customers", apiProperties.getHost(), apiProperties.getPort());
        logger.info("apiUrl = " + apiUrl);
    }

    @Override
    public List<CustomerResponse> findAll() {
        ResponseEntity<List<CustomerResponse>> responseEntity =
                restTemplate.exchange(apiUrl, HttpMethod.GET, null, new ParameterizedTypeReference<>(){});
        List<CustomerResponse> customerResponseList = responseEntity.getBody();
        return customerResponseList;
    }

    @Override
    public Optional<CustomerResponse> findById(Integer id) {
        try {
            CustomerResponse customerResponse = restTemplate.getForObject(apiUrl + "/{id}", CustomerResponse.class, id);
            return Optional.of(customerResponse);
        } catch (Exception e) {
            logger.error("Error", e);
            return Optional.empty();
        }
    }

    @Override
    public void insert(CustomerRequest customerRequest) {
        restTemplate.postForLocation(apiUrl, customerRequest);
    }
}
