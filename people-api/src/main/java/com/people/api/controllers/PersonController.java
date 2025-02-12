package com.people.api.controllers;

import com.people.api.entities.dto.PersonDTO;
import com.people.api.services.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/people")
public class PersonController {

  private final PersonService service;

  @GetMapping
  public ResponseEntity<List<PersonDTO>> getAllPersons() {
    return ResponseEntity.ok(service.listPersons());
  }
  
  @GetMapping("/{id}")
  public ResponseEntity<PersonDTO> getPerson(@PathVariable Long id) {
    return ResponseEntity.ok(service.getPerson(id));
  }

  @PostMapping
  public ResponseEntity<String> postPerson(@RequestBody PersonDTO dto) {
    service.createPerson(dto);
    return new ResponseEntity<>("ai ai ui ui", HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<String> updatePerson(@PathVariable Long id, @RequestBody PersonDTO dto) {
    service.updatePerson(id, dto);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> deletePerson(@PathVariable Long id) {
    service.deletePerson(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}