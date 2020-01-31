package com.letshadow.api.repository;

import com.letshadow.api.domain.Customer;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface CustomerRepository extends ElasticsearchRepository<Customer, String> {
    List<Customer> findByFirstName(String firstName);
}
