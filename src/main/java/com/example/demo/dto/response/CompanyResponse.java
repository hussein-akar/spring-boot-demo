package com.example.demo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CompanyResponse {

    private Long id;
    private Long countryId;
    private String countryName;
    private String name;
    private String acronym;
}