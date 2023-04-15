package com.example.demo.controller;

import com.example.demo.dto.payload.CreateCompanyPayload;
import com.example.demo.dto.response.CompanyResponse;
import com.example.demo.dto.response.ICompanyResponse;
import com.example.demo.service.company.CompanyService;
import com.example.demo.service.company.CreateCompanyService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/companies")
public class CompanyController {

    private final CompanyService companyService;
    private final CreateCompanyService createCompanyService;

    @GetMapping
    public ResponseEntity<List<CompanyResponse>> getCompanies() {
        List<CompanyResponse> companyResponses = companyService.getCompanyResponses();
        return ResponseEntity.ok(companyResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ICompanyResponse> getCompany(@PathVariable(value = "id") Long companyId) {
        return ResponseEntity.ok(companyService.getCompanyResponseById(companyId));
    }

    @PostMapping
    public ResponseEntity<CompanyResponse> createCompany(@RequestBody @Valid CreateCompanyPayload payload) {
        CompanyResponse companyResponse = createCompanyService.execute(payload);
        return new ResponseEntity<>(companyResponse, HttpStatus.CREATED);
    }
}