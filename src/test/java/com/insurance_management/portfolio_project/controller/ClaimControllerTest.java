package com.insurance_management.portfolio_project.controller;

import com.insurance_management.portfolio_project.repositories.ClaimRepositories;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ClaimControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ClaimRepositories claimRepositories;

    @InjectMocks
    private ClaimController claimController;

    @Test
    public void testFetchAllClaim() throws Exception {
        // Arrange
        when(claimRepositories.findAll()).thenReturn(Collections.emptyList());

        // Create MockMvc instance
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(claimController).build();

        // Act & Assert
        mockMvc.perform(get("/claim/all"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }
}

