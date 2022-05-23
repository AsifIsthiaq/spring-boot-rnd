package com.springdemo.springbootrnd.dao;

import com.springdemo.springbootrnd.models.Person;

import java.util.List;
import java.util.UUID;

public interface PersonDao {

    int insertPerson(UUID id, Person person);

    default int addPerson(Person person){
        UUID id = UUID.randomUUID();
        return insertPerson(id, person);
    }

    List<Person> selectAllPeople();
}
