package com.people.api.controllers;

import com.people.api.entities.dto.PersonDTO;
import com.people.api.services.PersonService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/person")
public class PersonController {

  private final PersonService personService;

  @GetMapping
  public ResponseEntity<List<PersonDTO>> listAllPersons() {
    List<PersonDTO> list = personService.listPersons();
    return new ResponseEntity<>(list, HttpStatus.OK);
  }
  
  @GetMapping("/{id}")
  public ResponseEntity<PersonDTO> getPersonById(@PathVariable Long id) {
    PersonDTO dto = personService.getPersonById(id);
    return new ResponseEntity<>(dto, HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<String> postPerson(@RequestBody @Valid PersonDTO dto) {
    personService.savePerson(dto);
    return new ResponseEntity<>("Person created with success", HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<String> updatePerson(@PathVariable Long id, @RequestBody @Valid PersonDTO dto) {
    personService.updatePerson(id, dto);
    return new ResponseEntity<>("Person updated with success", HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> deletePerson(@PathVariable Long id) {
    personService.deletePersonById(id);
    return new ResponseEntity<>("Person removed with success", HttpStatus.OK);
  }
}