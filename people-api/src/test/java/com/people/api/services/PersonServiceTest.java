package com.people.api.services;

import com.people.api.entities.Person;
import com.people.api.entities.dto.PersonDTO;
import com.people.api.exceptions.NotFoundException;
import com.people.api.repositories.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

  @InjectMocks
  private PersonService service;

  @Mock
  private PersonRepository repository;

  private Person person;
  private PersonDTO dto;
  private PersonService mockService;

  @BeforeEach
  void setUp() {
    person = Person.builder()
        .registrationNumber("123")
        .build();

    dto = PersonDTO.mapToDTO(person);
    mockService = mock(PersonService.class);
  }

  @Test
  void testShouldReturn_PersonById() {
    when(repository.findById(anyLong())).thenReturn(Optional.of(person));

    PersonDTO result = service.getPerson(anyLong());

    assertNotNull(result);
    assertEquals("123", result.getRegistrationNumber());
  }

  @Test
  void testShouldCreate_Person() {
    mockService.createPerson(dto);
    verify(mockService, times(1)).createPerson(dto);
  }

  @Test
  void testShouldThrow_NotFound() {
    doThrow(NotFoundException.class)
        .when(mockService).deletePerson(anyLong());

    assertThrows(NotFoundException.class, () -> mockService.deletePerson(anyLong()));
  }
}
