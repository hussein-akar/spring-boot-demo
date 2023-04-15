package com.example.demo.controller;

import com.example.demo.domain.Country;
import com.example.demo.service.CountryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/countries")
public class CountryController {

    private final CountryService countryService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Country>> getCountries() {
        List<Country> countries = countryService.getCountries();
        return ResponseEntity.ok(countries);
    }
}