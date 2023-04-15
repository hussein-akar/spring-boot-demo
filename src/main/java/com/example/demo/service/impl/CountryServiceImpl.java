package com.example.demo.service.impl;

import com.example.demo.domain.Country;
import com.example.demo.repository.CountryRepository;
import com.example.demo.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    @Override
    public Page<Country> getCountries(Pageable pageable) {
        return countryRepository.findAll(pageable);
    }
}