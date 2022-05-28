package com.springdemo.springbootrnd.services;

import com.springdemo.springbootrnd.dao.person.PersonDao;
import com.springdemo.springbootrnd.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PersonService {

    private final PersonDao personDao;

    @Autowired
    public PersonService(@Qualifier("postgres") PersonDao personDao) {
        this.personDao = personDao;
    }

    public int addPerson(Person person) {
        return personDao.addPerson(person);
    }

    public List<Person> selectAllPeople() {
        return personDao.selectAllPeople();
    }

    public Optional<Person> getPersonById(UUID id) {
        return personDao.selectPersonById(id);
    }

    public int deletePersonById(UUID id) {
        return personDao.deletePersonById(id);
    }

    public int updatePersonById(UUID id, Person person) {
        return personDao.updatePersonById(id, person);
    }
}
