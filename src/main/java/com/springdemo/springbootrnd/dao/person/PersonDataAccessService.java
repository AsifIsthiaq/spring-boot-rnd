package com.springdemo.springbootrnd.dao.person;

import com.springdemo.springbootrnd.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("postgres")
public class PersonDataAccessService implements PersonDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Person insertPerson(Person person) {
        final String sql = "INSERT INTO person (id, name) VALUES (?, ?)";
        int n = jdbcTemplate.update(sql, person.getId(), person.getName());
//        if(n==1) return person;
//        else //throw insertion failed exception
        System.out.println("New Person insertion ->" + n);
        System.out.println("New Person added " + person);
        return person;
    }

    @Override
    public List<Person> selectAllPeople() {
        final String sql = "SELECT id, name FROM person";
        return jdbcTemplate.query(sql, (resultSet, i) -> {
            UUID id = UUID.fromString(resultSet.getString("id"));
            String name = resultSet.getString("name");
            return new Person(id, name);
        });
    }

    @Override
    public Person selectPersonById(UUID id) {
        final String sql = "SELECT id, name FROM person where id = ?";
        Object[] obj = new Object[]{id};
        Person person = jdbcTemplate.queryForObject(sql, (resultSet, i) -> {
            UUID personId = UUID.fromString(resultSet.getString("id"));
            String name = resultSet.getString("name");
            return new Person(personId, name);
        }, obj);
        System.out.println("After querying selectPersonById: " + person);
        return person;
    }

    @Override
    public int deletePersonById(UUID id) {
        return 0;
    }

    @Override
    public int updatePersonById(UUID id, Person updatedPerson) {
        return 0;
    }
}
