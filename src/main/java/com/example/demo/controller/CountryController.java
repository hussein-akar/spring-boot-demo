package com.example.demo.controller;

import com.example.demo.domain.Country;
import com.example.demo.service.CountryService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
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

    @Parameters({
        @Parameter(name = "page", schema = @Schema(defaultValue = "0")),
        @Parameter(name = "size", schema = @Schema(defaultValue = "10"), example = "20"),
        @Parameter(name = "sort", schema = @Schema(defaultValue = "name"))
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<Country>> getCountries(@PageableDefault @SortDefault.SortDefaults(
        {@SortDefault(sort = "name", direction = Direction.ASC)}
    ) Pageable pageable) {
        Page<Country> countries = countryService.getCountries(pageable);
        return ResponseEntity.ok(countries);
    }
}