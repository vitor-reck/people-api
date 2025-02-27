package com.people.api.entities.model;

import com.people.api.entities.enums.GenderEnum;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "persons")
public class Person {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String name;

  @Enumerated(EnumType.STRING)
  private GenderEnum gender;

  private String registrationNumber;
  private LocalDate birthday;
  private String jobTitle;
}
