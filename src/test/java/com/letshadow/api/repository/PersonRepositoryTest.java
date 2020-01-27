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
        assertAll(
            ()->assertThat(people.get(0).getName()).isEqualTo("peter")
        );
    }

    @Test
    void getAllMonthOfBirthday() {
        List<Person> people = personRepository.findByMonthOfBirthday(5);
        assertAll(
            ()->assertThat(people.get(0).getName()).isEqualTo("peter")
        );
    }

    @Test
    void getAllFindByPersonName(){
        Person people = personRepository.findByPersonName("peter");
        assertThat(people.getName()).isEqualTo("peter");
    }

    @Test
    void get(){
        Person person = personRepository.findById(1L).orElse(null);
        assertThat(person.getName()).isEqualTo("peter");
    }

    @Test
    void findPeopleDeleted() {
        List<Person> people = personRepository.findPeopleDeleted();
        assertThat(people.size()).isEqualTo(1);
        assertThat(people.get(0).getName()).isEqualTo("kassie");
    }


}