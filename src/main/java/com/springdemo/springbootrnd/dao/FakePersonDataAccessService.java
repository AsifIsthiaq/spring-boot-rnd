package com.springdemo.springbootrnd.dao;

import com.springdemo.springbootrnd.models.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository("fakeDao")
public class FakePersonDataAccessService implements PersonDao{

    private static List<Person> DB = new ArrayList<>();

    @Override
    public int insertPerson(UUID id, Person person) {
        DB.add(new Person(id, person.getName()));
        System.out.println("Added "+ person.getName());
        return 1;
    }

    @Override
    public List<Person> selectAllPeople() {
        return DB;
    }
}
