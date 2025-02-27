package com.people.api.services;

import com.people.api.entities.model.Person;
import com.people.api.entities.dto.PersonDTO;
import com.people.api.repositories.PersonRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class PersonService {

  private final static String PERSON_NOT_FOUND = "Person not found";
  private final static String PERSON_ALREADY_PERSISTED = "Person already persisted";
  private final PersonRepository personRepository;

  public List<PersonDTO> listPersons(Pageable pageable) {
    return personRepository.findAll(pageable)
        .map(this::mapToDTO).getContent();
  }

  public PersonDTO getPersonById(Long id) {
    log.info("Retrieving person with id: {}", id);

    return personRepository.findById(id)
        .map(this::mapToDTO)
        .orElseThrow(() -> new EntityNotFoundException(PERSON_NOT_FOUND));
  }

  public PersonDTO getPersonByRegistrationNumber(String registrationNumber) {
    log.info("Retrieving person with registration number: {}", registrationNumber);

    return personRepository.findByRegistrationNumber(registrationNumber)
        .map(this::mapToDTO)
        .orElseThrow(() -> new EntityNotFoundException(PERSON_NOT_FOUND));
  }

  public void savePerson(PersonDTO dto) {
    personRepository.findByRegistrationNumber(dto.getRegistrationNumber())
        .ifPresent(person -> {throw new EntityExistsException(PERSON_ALREADY_PERSISTED);});

    Long savedPersonId = personRepository.save(mapToEntity(dto)).getId();
    log.info("Person with id {}: has been created", savedPersonId);
  }

  public void updatePerson(Long id, PersonDTO dto) {
    personRepository.findById(id)
        .ifPresentOrElse(person -> {
          person.setName(dto.getName());
          person.setGender(dto.getGender());
          person.setRegistrationNumber(dto.getRegistrationNumber());
          person.setBirthday(dto.getBirthday());
          person.setJobTitle(dto.getJobTitle());

          personRepository.save(person);
          log.info("Person with id {}: has been updated", id);
        },
            () -> {throw new EntityNotFoundException(PERSON_NOT_FOUND);});
  }

  public void deletePersonById(Long id) {
    personRepository.findById(id)
        .ifPresentOrElse(person -> {
          personRepository.deleteById(id);

          log.info("Person with id {}: has been removed", id);
        },
            () -> {throw new EntityNotFoundException(PERSON_NOT_FOUND);});
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
