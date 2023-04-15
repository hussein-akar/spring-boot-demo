package com.example.demo.mapper;

import com.example.demo.domain.Company;
import com.example.demo.dto.response.CompanyResponse;

public class CompanyMapper {

    public static CompanyResponse toCompanyResponse(Company company) {
        return new CompanyResponse(company.getId(), company.getCountry().getId(), company.getCountry().getName(), company.getName(),
            company.getAcronym());
    }
}
