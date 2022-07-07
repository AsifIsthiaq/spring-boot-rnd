package com.springdemo.springbootrnd.controllers;

import com.springdemo.springbootrnd.models.Person;
import com.springdemo.springbootrnd.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RequestMapping("api/person")
@RestController
public class PersonController {
    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public ResponseEntity<Person> addPerson(@Valid @NonNull @RequestBody Person person) {
        return new ResponseEntity(personService.addPerson(person), HttpStatus.CREATED);
    }

    @GetMapping
    public List<Person> getAllPeople() {
        return personService.selectAllPeople();
    }

    @GetMapping(path = "{id}")
    public Person getPersonById(@PathVariable("id") UUID id) {
        return personService.getPersonById(id);
    }

    @DeleteMapping(path = "{id}")
    public int deletePersonById(@PathVariable("id") UUID id) {
        return personService.deletePersonById(id);
    }

    @PutMapping(path = "{id}")
    public int updatePersonById(@PathVariable("id") UUID id, @Valid @NonNull @RequestBody Person person) {
        return personService.updatePersonById(id, person);
    }
}
