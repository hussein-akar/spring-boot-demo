package com.example.demo.service;

import com.example.demo.domain.Country;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CountryService {

    Page<Country> getCountries(Pageable pageable);
}