package com.example.api.web.controller;

import com.example.api.persistence.entity.Customer;
import com.example.api.service.CustomerService;
import com.example.api.web.request.CustomerRequest;
import com.example.api.web.response.CustomerResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    /** 必要があれば、デバッグ時のログ出力に使ってください */
    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    /**
     * 社員一覧を返すコントローラーメソッド。
     */
    @GetMapping
    public List<CustomerResponse> getAll() {
        List<Customer> customers = customerService.findAll();
        List<CustomerResponse> customerResponseList = customers.stream()
                .map(customer -> new CustomerResponse(customer))
                .collect(Collectors.toList());
        return customerResponseList;
    }

    /**
     * 社員一覧を返すコントローラーメソッド。
     */
    @GetMapping("/{id}")
    public CustomerResponse getById(@PathVariable Integer id) {
        Optional<Customer> customerOptional = customerService.findById(id);
        CustomerResponse customerResponse = customerOptional.map(customer -> new CustomerResponse(customer))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer id = " + id + " not found"));
        return customerResponse;
    }

    /**
     * 社員の追加を行うコントローラーメソッド。
     */
    @PostMapping
    public ResponseEntity insertComplete(@Validated @RequestBody CustomerRequest customerRequest) {
        // Requestをエンティティに変換
        Customer customer = customerRequest.convertToEntity();
        customerService.insert(customer);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .pathSegment(customer.getId().toString())
                .build().expand().toUri();
        return ResponseEntity.created(location).build();
    }
}