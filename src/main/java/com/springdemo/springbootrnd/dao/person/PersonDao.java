package com.springdemo.springbootrnd.dao.person;

import com.springdemo.springbootrnd.models.Person;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersonDao {

    Person insertPerson(Person person);

    default Person addPerson(Person person){
        person.setId(UUID.randomUUID());
        return insertPerson(person);
    }

    List<Person> selectAllPeople();

    Person selectPersonById(UUID id);

    int deletePersonById(UUID id);

    int updatePersonById(UUID id, Person person);
}
