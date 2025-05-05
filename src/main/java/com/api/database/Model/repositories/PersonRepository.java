package com.api.database.Model.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.database.Model.entities.Person;

public interface PersonRepository extends JpaRepository<Person, Long>{
    Optional<Person> findByNameContains(String name);
}
