package com.example.demo.service.company;

import com.example.demo.dto.payload.CreateCompanyPayload;
import com.example.demo.dto.response.CompanyResponse;
import org.springframework.transaction.annotation.Transactional;

public interface CreateCompanyService {

    CompanyResponse execute(CreateCompanyPayload payload);
}