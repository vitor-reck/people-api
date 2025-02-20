package com.people.api.entities.dto;

import com.people.api.entities.enums.GenderEnum;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class PersonDTO {

    @NotNull(message = "Name cannot be null")
    private String name;

    @NotNull(message = "Gender can't be null")
    private GenderEnum gender;

    @NotNull(message = "Registration number can't be null")
    private String registrationNumber;
    private LocalDate birthday;
    private String jobTitle;
}
