package com.people.api.repositories;

import com.people.api.entities.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
  Optional<Person> findByRegistrationNumber(String registrationNumber);
}
