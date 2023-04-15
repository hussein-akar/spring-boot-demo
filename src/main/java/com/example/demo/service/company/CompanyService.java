package com.example.demo.service.company;

import com.example.demo.dto.response.CompanyResponse;
import com.example.demo.dto.response.ICompanyResponse;
import java.util.List;

public interface CompanyService {

    List<CompanyResponse> getCompanyResponses();

    ICompanyResponse getCompanyResponseById(long companyId);
}