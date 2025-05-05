package com.api.database.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.api.database.Model.entities.Person;
import com.api.database.Model.repositories.PersonRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PersonService {
    private PersonRepository personRepository;

    public Person save (Person person) {
        Optional<Person> searchedPerson = personRepository.findByNameContains(person.getName());
        if (searchedPerson.isPresent()) {
            throw new PersonAlreadyAddedException();
        }
        return personRepository.save(person);
    }

    public List<Person> fetchAll() {
        return personRepository.findAll();
    }
}
