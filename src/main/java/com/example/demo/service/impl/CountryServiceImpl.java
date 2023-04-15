package com.example.demo.service.impl;

import com.example.demo.domain.Country;
import com.example.demo.repository.CountryRepository;
import com.example.demo.service.CountryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    @Override
    public List<Country> getCountries() {
        return (List<Country>) countryRepository.findAll();
    }
}