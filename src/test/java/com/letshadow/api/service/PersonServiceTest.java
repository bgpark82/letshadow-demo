package com.letshadow.api.service;

import com.letshadow.api.domain.Person;
import com.letshadow.api.exception.PersonNotFoundException;
import com.letshadow.api.repository.PersonRepository;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

    @InjectMocks
    private PersonService personService;

    @Mock
    private PersonRepository personRepository;

    @Test
    void getList(){
        when(personRepository.findAll(any(Pageable.class)))
                .thenReturn(new PageImpl<>(Lists.newArrayList(Person.of("peter"))));
        Page<Person> people = personService.getList(PageRequest.of(1,1));
        assertThat(people.getNumberOfElements()).isEqualTo(1);
        assertThat(people.getContent().get(0).getName()).isEqualTo("peter");
    }

    @Test
    void modifyIfPersonNotFound() {
        when(personRepository.findById(1L))
                .thenReturn(Optional.empty());
        assertThrows(PersonNotFoundException.class, ()-> personService.modifyPerson(1L,mockPersonDto()));
    }

    private Person.PersonDTO mockPersonDto(){
        return Person.PersonDTO.of("martin","판교", LocalDate.now(),"programmer","010-1111-2222");
    }
}
