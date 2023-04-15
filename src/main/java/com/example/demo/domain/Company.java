package com.example.demo.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "T_Company")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(length = 10)
    private String acronym;

    private Company(Country country, String name, String acronym) {
        this.country = country;
        this.name = name;
        this.acronym = acronym;
    }

    public static class CompanyBuilder {

        private Country country;
        private String name;
        private String acronym = "";

        public CompanyBuilder(Country country, String name) {
            this.country = country;
            this.name = name;
        }

        public CompanyBuilder withAcronym(String acronym) {
            if (acronym == null) {
                acronym = "";
            }

            this.acronym = acronym;
            return this;
        }

        public Company build() {
            return new Company(country, name, acronym);
        }
    }
}