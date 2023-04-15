package com.example.demo.repository;

import com.example.demo.domain.Country;
import org.springframework.data.repository.CrudRepository;

public interface CountryRepository extends CrudRepository<Country, Long> {

}