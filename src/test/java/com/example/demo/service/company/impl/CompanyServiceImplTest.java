package com.example.demo.service.company.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.example.demo.core.exception.ForbiddenException;
import com.example.demo.dto.response.CompanyResponse;
import com.example.demo.dto.response.ICompanyResponse;
import com.example.demo.repository.CompanyRepository;
import com.example.demo.repository.CompanyRepositoryCustom;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CompanyServiceImplTest {

    public static final long COMPANY_ID = 1L;
    public static final long INVALID_COMPANY_ID = 404L;

    @Mock
    private CompanyRepository companyRepository;
    @Mock
    private CompanyRepositoryCustom companyRepositoryCustom;

    @InjectMocks
    private CompanyServiceImpl underTest;

    @Nested
    class getCompanyResponses {

        @Test
        void shouldReturnListOfCompanyResponsesWhenCalled() {
            when(companyRepositoryCustom.getCompanyResponses()).thenReturn(List.of(mock(CompanyResponse.class), mock(CompanyResponse.class)));

            List<CompanyResponse> actual = underTest.getCompanyResponses();

            assertThat(actual).hasSize(2);
        }
    }

    @Nested
    class getCompanyResponseById {

        @Test
        void shouldThrowForbiddenExceptionWhenCompanyIdIsNotValid() {
            Throwable throwable = catchThrowable(() -> underTest.getCompanyResponseById(INVALID_COMPANY_ID));

            assertThat(throwable).isInstanceOf(ForbiddenException.class).hasMessage("Access Forbidden.");
        }

        @Test
        void shouldReturnCompanyResponseWhenCompanyIdIsValid() {
            ICompanyResponse companyResponse = new CompanyResponseImpl(1L, 1L, "Country Name", "Company-X", "CompanyX");

            when(companyRepository.getCompanyResponseById(COMPANY_ID)).thenReturn(Optional.of(companyResponse));

            var actual = underTest.getCompanyResponseById(COMPANY_ID);

            assertThat(actual.getId()).isEqualTo(COMPANY_ID);
            assertThat(actual.getCountryId()).isEqualTo(1L);
            assertThat(actual.getCountryName()).isEqualTo("Country Name");
            assertThat(actual.getName()).isEqualTo("Company-X");
            assertThat(actual.getAcronym()).isEqualTo("CompanyX");
        }

        private class CompanyResponseImpl extends CompanyResponse implements ICompanyResponse {

            public CompanyResponseImpl(Long id, Long countryId, String countryName, String name, String acronym) {
                super(id, countryId, countryName, name, acronym);
            }

            @Override
            public Long getId() {
                return super.getId();
            }

            @Override
            public Long getCountryId() {
                return super.getCountryId();
            }

            @Override
            public String getCountryName() {
                return super.getCountryName();
            }

            @Override
            public String getName() {
                return super.getName();
            }

            @Override
            public String getAcronym() {
                return super.getAcronym();
            }
        }
    }
}