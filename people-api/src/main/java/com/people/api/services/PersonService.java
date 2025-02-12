package com.people.api.services;

import com.people.api.entities.Person;
import com.people.api.entities.dto.PersonDTO;
import com.people.api.repositories.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonService {

  private final PersonRepository repository;

  public List<PersonDTO> listPersons() {
    return repository.findAll().stream()
        .map(PersonDTO::mapToDTO)
        .collect(Collectors.toList());
  }

  public PersonDTO getPerson(Long id) {
    Optional<Person> optPerson = repository.findById(id);

    if(optPerson.isPresent())
      return PersonDTO.mapToDTO(optPerson.get());
    else
      return null;
  }

  public void createPerson(PersonDTO dto) {
    Person entity = PersonDTO.mapToEntity(dto);
    repository.save(entity);
  }

  public void updatePerson(Long id, PersonDTO dto) {
    Optional<Person> optPerson = repository.findById(id);

    if(optPerson.isPresent()) {
      Person entity = PersonDTO.mapToEntity(dto);
      entity.setId(id);
      repository.save(entity);
    }
  }

  public void deletePerson(Long id) {
    Optional<Person> optPerson = repository.findById(id);

    if(optPerson.isPresent()) {
      Person entity = optPerson.get();
      repository.delete(entity);
    }
  }
}
