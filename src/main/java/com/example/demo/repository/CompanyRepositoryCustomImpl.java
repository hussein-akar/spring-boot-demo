package com.example.demo.repository;

import com.example.demo.dto.response.CompanyResponse;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.intellij.lang.annotations.Language;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CompanyRepositoryCustomImpl implements CompanyRepositoryCustom {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private CompanyResponse toCompanyResponse(ResultSet rs, int row) throws SQLException {
        return new CompanyResponse(rs.getLong("id"),
            rs.getLong("countryId"),
            rs.getString("countryName"),
            rs.getString("name"),
            rs.getString("acronym"));
    }

    @Override
    public List<CompanyResponse> getCompanyResponses() {
        String SQL_SELECT_ALL_COUNTRIES_ORDER_BY_NAME = """
            SELECT
            c.id                id,
            co.id               countryId,
            co.name     countryName,
            c.name              name,
            c.acronym           acronym
            FROM T_COMPANY c
            JOIN T_COUNTRY co on c.country_id = co.id
            ORDER BY c.name
            """;
        return namedParameterJdbcTemplate.query(SQL_SELECT_ALL_COUNTRIES_ORDER_BY_NAME,
            new MapSqlParameterSource(),
            this::toCompanyResponse
        );
    }
}