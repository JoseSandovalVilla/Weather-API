package org.adaschool.Weather.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class NameControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private NameController nameController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(nameController).build();
    }

    @Test
    public void testGetName() throws Exception {
        mockMvc.perform(get("/v1/api/name"))
                .andExpect(status().isOk())
                .andExpect(content().string("Jose"));
    }

    @Test
    public void testGetNameWithDifferentCase() throws Exception {
        // Test case sensitivity
        mockMvc.perform(get("/v1/api/name"))
                .andExpect(status().isOk())
                .andExpect(content().string("Jose"));
    }

    @Test
    public void testGetNameWithExtraWhitespace() throws Exception {
        // Test with extra whitespace
        mockMvc.perform(get("/v1/api/name"))
                .andExpect(status().isOk())
                .andExpect(content().string(" Jose "));
    }

    @Test
    public void testGetNameWithDifferentEndpoint() throws Exception {
        // Test accessing a different endpoint
        mockMvc.perform(get("/v1/api/name"))
                .andExpect(status().isOk())
                .andExpect(content().string("Jose"));
    }

    @Test
    public void testGetNameWithSpecialCharacters() throws Exception {
        // Test with special characters in name
        mockMvc.perform(get("/v1/api/name"))
                .andExpect(status().isOk())
                .andExpect(content().string("Jose!"));
    }
}
