package com.letshadow.api.controller;

import com.letshadow.api.domain.Person;
import com.letshadow.api.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/person")
public class PersonController {

    private final PersonService personService;

    @GetMapping
    public Page<Person> getList(@PageableDefault(page = 1) Pageable pageable){
        return personService.getList(pageable);
    }

    @GetMapping("/{id}")
    public Person getPerson(@PathVariable Long id){
        return personService.getPerson(id);
    }

    @GetMapping("/{name}")
    public Person getPersonByName(@PathVariable String name){
        return personService.getPersonByName(name);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void savePerson(@RequestBody @Valid Person.PersonDTO personDto) {
        personService.savePerson(personDto);
    }

    @PutMapping("/{id}")
    public void modifyPerson(@PathVariable Long id, @RequestBody Person.PersonDTO personDTO){
        personService.modifyPerson(id, personDTO);
    }





}
