package com.example.demo.repository;

import com.example.demo.domain.Company;
import com.example.demo.dto.response.ICompanyResponse;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    boolean existsByCountryIdAndName(long countryId, String name);

    @Query(value = """
        SELECT c.id as id, c.country.id as countryId, c.country.name as countryName, c.name as name, c.acronym as acronym
        FROM Company c
        WHERE c.id = :companyId
        """)
    Optional<ICompanyResponse> getCompanyResponseById(long companyId);
}