package com.letshadow.api.repository;

import com.letshadow.api.domain.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PersonRepositoryTest {

    @Autowired
    private PersonRepository personRepository;

    @Test
    void getAll(){
        List<Person> people = personRepository.findAll();
        System.out.println(people);
        assertThat(people.size()).isEqualTo(1);
        assertThat(people.get(0).getName()).isEqualTo("peter");
    }

    @Test
    void get(){
        Person person = personRepository.findById(1L).orElse(null);
        assertThat(person.getName()).isEqualTo("peter");
    }
}