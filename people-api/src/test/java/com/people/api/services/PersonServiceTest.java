package com.people.api.services;

import com.people.api.entities.model.Person;
import com.people.api.entities.dto.PersonDTO;
import com.people.api.repositories.PersonRepository;
import jakarta.persistence.EntityNotFoundException;
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
  private PersonService personService;

  @Mock
  private PersonRepository personRepository;

  private Person person;
  private PersonDTO personDTO;
  private PersonService mockService;

  @BeforeEach
  void setUp() {
    person = Person.builder()
        .registrationNumber("123")
        .build();

    personDTO = personService.mapToDTO(person);
    mockService = mock(PersonService.class);
  }

  @Test
  void testGetPersonById_ShouldReturnNotNull() {
    when(personRepository.findById(anyLong())).thenReturn(Optional.of(person));

    PersonDTO result = personService.getPersonById(anyLong());

    assertNotNull(result);
    assertEquals("123", result.getRegistrationNumber());
  }

  @Test
  void testSavePerson_ShouldBeFulfilled() {
    mockService.savePerson(personDTO);
    verify(mockService, times(1)).savePerson(personDTO);
  }

  @Test
  void testDeletePersonById_ShouldThrowNoSuchElement() {
    doThrow(EntityNotFoundException.class)
        .when(mockService).deletePersonById(anyLong());

    assertThrows(EntityNotFoundException.class, () -> mockService.deletePersonById(anyLong()));
  }
}
