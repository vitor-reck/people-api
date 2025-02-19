package com.people.api.controllers;

import com.people.api.entities.dto.PersonDTO;
import com.people.api.services.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PersonControllerTest {

  @InjectMocks
  private PersonController personController;

  @Mock
  private PersonService personService;
  private PersonDTO personDTO;

  @BeforeEach
  void setUp() {
    personDTO = PersonDTO.builder()
        .registrationNumber("123")
        .build();
  }

  @Test
  void testPostPerson_ShouldReturn_StatusCreated() {
    ResponseEntity<String> result = personController.postPerson(personDTO);

    assertEquals(HttpStatus.CREATED, result.getStatusCode());
  }

  @Test
  void testGetPersonById_ShouldReturn_StatusOK_And_DTO() {
    when(personService.getPersonById(anyLong())).thenReturn(personDTO);

    ResponseEntity<PersonDTO> result = personController.getPersonById(anyLong());

    assertEquals(HttpStatus.OK, result.getStatusCode());
    assertEquals(personDTO, result.getBody());
  }
}
