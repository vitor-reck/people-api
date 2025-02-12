package com.people.api.entities.dto;

import com.people.api.entities.Person;
import com.people.api.entities.enums.GenderEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class PersonDTO {

  private String name;
  private GenderEnum gender;
  private String registrationNumber;
  private LocalDate birthday;
  private String jobTitle;

  public static PersonDTO mapToDTO(Person person) {
    return PersonDTO.builder()
        .name(person.getName())
        .gender(person.getGender())
        .registrationNumber(person.getRegistrationNumber())
        .birthday(person.getBirthday())
        .jobTitle(person.getJobTitle())
        .build();
  }

  public static Person mapToEntity(PersonDTO dto) {
    return Person.builder()
        .name(dto.getName())
        .gender(dto.getGender())
        .registrationNumber(dto.getRegistrationNumber())
        .birthday(dto.getBirthday())
        .jobTitle(dto.getJobTitle())
        .build();
  }
}
