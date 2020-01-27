package com.letshadow.api.service;

import com.letshadow.api.domain.Person;
import com.letshadow.api.exception.PersonNotFoundException;
import com.letshadow.api.exception.RenameNotPermittedException;
import com.letshadow.api.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;

    public Page<Person> getList(Pageable pageable) {
        return personRepository.findAll(pageable);
    }

    public Person getPerson(Long id) {
        return personRepository.findById(id).orElse(null);
    }

    public Person getPersonByName(String name) {
        return personRepository.findByPersonName(name);
    }

    @Transactional
    public void savePerson(Person.PersonDTO personDTO) {
        Person person = new Person(personDTO);
        person.setName(personDTO.getName());
        personRepository.save(person);
    }

    @Transactional
    public void modifyPerson(Long id, Person.PersonDTO personDTO){
        Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
        if(!person.getName().equals(personDTO.getName())){
            throw new RenameNotPermittedException();
        }
        person.set(personDTO);
        personRepository.save(person);
    }

}
