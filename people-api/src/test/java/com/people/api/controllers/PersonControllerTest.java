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
  private PersonController controller;

  @Mock
  private PersonService service;
  private PersonDTO dto;

  @BeforeEach
  void setUp() {
    dto = PersonDTO.builder()
        .registrationNumber("123")
        .build();
  }

  @Test
  void testShouldReturn_StatusCreated() {
    ResponseEntity<String> result = controller.postPerson(dto);

    assertEquals(HttpStatus.CREATED, result.getStatusCode());
  }

  @Test
  void testShouldReturn_StatusOK_And_GetBodyMessage() {
    when(service.getPerson(anyLong())).thenReturn(dto);

    ResponseEntity<PersonDTO> result = controller.getPerson(anyLong());

    assertEquals(HttpStatus.OK, result.getStatusCode());
    assertEquals(dto, result.getBody());
  }
}
