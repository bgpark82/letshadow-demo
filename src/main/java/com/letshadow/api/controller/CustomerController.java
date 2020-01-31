package com.letshadow.api.controller;

import com.letshadow.api.domain.Customer;
import com.letshadow.api.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @PostMapping("/save")
    public int save(@RequestBody List<Customer> customers){
        customerRepository.saveAll(customers);
        return customers.size();
    }

    @GetMapping("/findAll")
    public Iterable<Customer> findAll(){
        return customerRepository.findAll();
    }

    @GetMapping("/{firstName}")
    public List<Customer> findByFirstName(@PathVariable  String firstName){
        return customerRepository.findByFirstName(firstName);
    }
}
