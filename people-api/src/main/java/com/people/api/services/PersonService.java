package com.people.api.services;

import com.people.api.entities.model.Person;
import com.people.api.entities.dto.PersonDTO;
import com.people.api.exceptions.EntityNotFoundException;
import com.people.api.repositories.PersonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class PersonService {

  private final PersonRepository personRepository;
  private static final String PERSON_NOT_FOUND = "Person not found";

  public List<PersonDTO> listPersons() {
    return personRepository.findAll().stream()
        .map(this::mapToDTO)
        .collect(Collectors.toList());
  }

  public PersonDTO getPersonById(Long id) {
    log.info("Retrieving person by id: {}", id);
    Optional<Person> optPerson = personRepository.findById(id);

    if(optPerson.isPresent())
      return this.mapToDTO(optPerson.get());
    else
      throw new EntityNotFoundException(PERSON_NOT_FOUND);
  }

  public void savePerson(PersonDTO dto) {
    Person entity = mapToEntity(dto);

    personRepository.save(entity);
    log.info("Person by id {}: has been created", entity.getId());
  }

  public void updatePerson(Long id, PersonDTO dto) {
    Optional<Person> optPerson = personRepository.findById(id);

    if(optPerson.isPresent()) {
      Person entity = this.mapToEntity(dto);
      entity.setId(id);
      personRepository.save(entity);
      log.info("Person by id {}: has been updated", entity.getId());
    }
    else {
      throw new EntityNotFoundException(PERSON_NOT_FOUND);
    }
  }

  public void deletePersonById(Long id) {
    Optional<Person> optPerson = personRepository.findById(id);

    if(optPerson.isPresent()) {
      Person entity = optPerson.get();
      personRepository.delete(entity);
      log.info("Person by id {}: has been removed", entity.getId());
    }
    else {
      throw new EntityNotFoundException(PERSON_NOT_FOUND);
    }
  }

  public PersonDTO mapToDTO(Person person) {
    return PersonDTO.builder()
        .name(person.getName())
        .gender(person.getGender())
        .registrationNumber(person.getRegistrationNumber())
        .birthday(person.getBirthday())
        .jobTitle(person.getJobTitle())
        .build();
  }

  public Person mapToEntity(PersonDTO personDTO) {
    return Person.builder()
        .name(personDTO.getName())
        .gender(personDTO.getGender())
        .registrationNumber(personDTO.getRegistrationNumber())
        .birthday(personDTO.getBirthday())
        .jobTitle(personDTO.getJobTitle())
        .build();
  }
}
