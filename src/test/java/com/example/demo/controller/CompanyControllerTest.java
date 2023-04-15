package com.example.demo.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.demo.dto.payload.CreateCompanyPayload;
import com.example.demo.dto.response.CompanyResponse;
import com.example.demo.service.company.CompanyService;
import com.example.demo.service.company.CreateCompanyService;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(CompanyController.class)
class CompanyControllerTest {

    @MockBean
    private CompanyService companyService;
    @MockBean
    private CreateCompanyService createCompanyService;

    @Autowired
    private MockMvc mockMvc;

    @Nested
    class createCompany {

        @Test
        void shouldReturnUnauthenticatedWhenUserNotLoggedIn() throws Exception {
            mockMvc.perform(
                    post("/api/v1/companies")
                        .with(csrf())
                        .content("""
                                                        
                            """))
                .andExpect(status().isUnauthorized());
        }

        @Test
        @WithMockUser(username = "user")
        void shouldReturnBadRequestWhenDataNotValid() throws Exception {
            mockMvc.perform(
                    post("/api/v1/companies")
                        .with(csrf())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(
                            """
                                {
                                   "name": "Test Company"
                                }
                                """
                        ))
                .andExpect(status().isBadRequest());
        }

        @Test
        @WithMockUser(username = "user")
        void shouldReturnCreatedWhenDataIsValidAndUserLoggedIn() throws Exception {
            CompanyResponse companyResponse = new CompanyResponse(10L, 12L, "Country", "Test Company", "");

            Mockito.when(createCompanyService.execute(any(CreateCompanyPayload.class))).thenReturn(companyResponse);

            mockMvc.perform(
                    post("/api/v1/companies")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(
                            """
                                {
                                   "countryId": "12",
                                   "name": "Test Company"
                                }
                                """
                        )
                        .with(csrf()))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(10L))
                .andExpect(jsonPath("$.countryId").value(12L))
                .andExpect(jsonPath("$.name").value("Test Company"))
                .andExpect(jsonPath("$.acronym").value(""));
        }
    }
}