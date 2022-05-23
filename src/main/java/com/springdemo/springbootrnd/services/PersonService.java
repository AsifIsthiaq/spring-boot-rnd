package com.springdemo.springbootrnd.services;

import com.springdemo.springbootrnd.dao.PersonDao;
import com.springdemo.springbootrnd.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    private final PersonDao personDao;

    @Autowired
    public PersonService(@Qualifier("fakeDao") PersonDao personDao) {
        this.personDao = personDao;
    }

    public int addPerson(Person person){
        return personDao.addPerson(person);
    }

    public List<Person> selectAllPeople(){
        return personDao.selectAllPeople();
    }
}
