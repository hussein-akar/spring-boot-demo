package com.example.demo.dto.payload;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

@Getter
public class CreateCompanyPayload {

    @NotNull
    @Min(1)
    private Long countryId;

    @NotBlank
    @Length(min = 3, max = 50)
    private String name;

    @Length(max = 10)
    private String acronym;
}