package com.example.demo.repository;

import com.example.demo.dto.response.CompanyResponse;
import java.util.List;

public interface CompanyRepositoryCustom {

    List<CompanyResponse> getCompanyResponses();
}