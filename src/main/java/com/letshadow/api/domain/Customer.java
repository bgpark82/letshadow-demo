package com.letshadow.api.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName="javatechie", type="customer", shards = 2)
public class Customer {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    private int age;

}
