package com.springdemo.springbootrnd.dao.person;

import com.springdemo.springbootrnd.models.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
//import java.util.Optional;
import java.util.UUID;

@Repository("fakeDao")
public class FakePersonDataAccessService implements PersonDao {

    private final List<Person> DB = new ArrayList<>();

    @Override
    public Person insertPerson(Person person) {
        DB.add(new Person(person.getId(), person.getName()));
        System.out.println("Added " + person.getName());
        return person;
    }

    @Override
    public List<Person> selectAllPeople() {
        return DB;
    }

//    public Optional<Person> selectPersonById(UUID id) {
//        return DB.stream().filter(person -> person.getId().equals(id)).findFirst();
//    }
    @Override
    public Person selectPersonById(UUID id) {
        return null;
    }

    @Override
    public int deletePersonById(UUID id) {
//        Optional<Person> person = selectPersonById(id);
//        if (person.isEmpty()) return 0;
//        DB.remove(person.get());
        return 1;
    }

    @Override
    public int updatePersonById(UUID id, Person updatedPerson) {
//        return selectPersonById(id).map(person -> {
//            int indexOfPersonToUpdate = DB.indexOf(person);
//            System.out.println("indexOfPersonToUpdate-> " + indexOfPersonToUpdate);
//            if (indexOfPersonToUpdate >= 0) {
//                DB.set(indexOfPersonToUpdate, new Person(id, updatedPerson.getName()));
//                return 1;
//            }
//            return 0;
//        }).orElse(0);
        return 0;
    }
}
