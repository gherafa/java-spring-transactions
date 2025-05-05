package com.api.database.Controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.database.Model.entities.Person;
import com.api.database.Model.entities.PersonRequestDto;
import com.api.database.Model.entities.PersonResponseDto;
import com.api.database.Service.PersonService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PersonController {
    @Autowired
    private final PersonService personService;

    @PostMapping("/persons")
    public ResponseEntity<PersonResponseDto> addPerson(@RequestBody PersonRequestDto personRequestDto) {
        Person person = personRequestDto.convertToPersonEntity();
        personService.save(person);
        PersonResponseDto personResponseDto = person.convertToPersonResponseDto();

        return new ResponseEntity<>(personResponseDto, HttpStatus.CREATED);
    }

    @GetMapping("/persons")
    public List<PersonResponseDto> fetchAllPersons() {
        List<Person> persons = personService.fetchAll();

        return persons.stream().map(Person::convertToPersonResponseDto).collect(Collectors.toList());
    }
}
