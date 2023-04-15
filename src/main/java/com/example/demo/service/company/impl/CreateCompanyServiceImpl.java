package com.example.demo.service.company.impl;

import com.example.demo.core.exception.CompanyNameExistsException;
import com.example.demo.core.exception.ForbiddenException;
import com.example.demo.domain.Company;
import com.example.demo.domain.Country;
import com.example.demo.dto.payload.CreateCompanyPayload;
import com.example.demo.dto.response.CompanyResponse;
import com.example.demo.mapper.CompanyMapper;
import com.example.demo.repository.CompanyRepository;
import com.example.demo.repository.CountryRepository;
import com.example.demo.service.company.CreateCompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateCompanyServiceImpl implements CreateCompanyService {

    private final CountryRepository countryRepository;
    private final CompanyRepository companyRepository;

    @Override
    @Transactional
    public CompanyResponse execute(CreateCompanyPayload payload) {
        Country country = countryRepository.findById(payload.getCountryId()).orElseThrow(ForbiddenException::new);

        this.validateCompanyName(payload);

        Company company = new Company.CompanyBuilder(country, payload.getName()).withAcronym(payload.getAcronym()).build();
        companyRepository.save(company);

        return CompanyMapper.toCompanyResponse(company);
    }

    private void validateCompanyName(CreateCompanyPayload payload) {
        if (companyRepository.existsByCountryIdAndName(payload.getCountryId(), payload.getName())) {
            throw new CompanyNameExistsException();
        }
    }
}