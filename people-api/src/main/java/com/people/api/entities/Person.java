package com.people.api.entities;

import com.people.api.entities.enums.GenderEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Person {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String name;
  private GenderEnum gender;

  @Column(unique = true)
  private String registrationNumber;
  private LocalDate birthday;
  private String jobTitle;
}
