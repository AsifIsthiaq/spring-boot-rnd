package com.springdemo.springbootrnd.controllers;

import com.springdemo.springbootrnd.models.Person;
import com.springdemo.springbootrnd.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("api/v1/person")
@RestController
public class PersonController {
    private final PersonService personService;
    // tesing token

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public int addPerson(@RequestBody Person person) {
        return personService.addPerson(person);
    }

    @GetMapping
    public List<Person> getAllPeople() {
        return personService.selectAllPeople();
    }

    @GetMapping(path = "{id}")
    public Person getPersonById(@PathVariable("id") UUID id) {
        return personService.getPersonById(id).orElse(null);
    }

    @DeleteMapping (path = "{id}")
    public int deletePersonById(@PathVariable("id") UUID id) {
        return personService.deletePersonById(id);
    }

    @PutMapping  (path = "{id}")
    public int updatePersonById(@PathVariable("id") UUID id, @RequestBody Person person) {
        return personService.updatePersonById(id,person);
    }
}
