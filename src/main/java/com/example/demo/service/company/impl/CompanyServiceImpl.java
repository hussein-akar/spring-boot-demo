package com.example.demo.service.company.impl;

import com.example.demo.core.exception.ForbiddenException;
import com.example.demo.dto.response.CompanyResponse;
import com.example.demo.dto.response.ICompanyResponse;
import com.example.demo.repository.CompanyRepository;
import com.example.demo.repository.CompanyRepositoryCustom;
import com.example.demo.service.company.CompanyService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyRepositoryCustom companyRepositoryCustom;

    @Override
    public List<CompanyResponse> getCompanyResponses() {
        return companyRepositoryCustom.getCompanyResponses();
    }

    @Override
    public ICompanyResponse getCompanyResponseById(long companyId) {
        return companyRepository.getCompanyResponseById(companyId).orElseThrow(ForbiddenException::new);
    }
}