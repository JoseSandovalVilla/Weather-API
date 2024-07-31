package org.adaschool.Weather.controller;

import org.adaschool.Weather.data.WeatherReport;
import org.adaschool.Weather.service.WeatherReportService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class WeatherReportControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private WeatherReportController weatherReportController;

    @Mock
    private WeatherReportService weatherReportService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(weatherReportController).build();
    }

    @Test
    public void testGetWeatherReport() throws Exception {
        WeatherReport mockReport = new WeatherReport();
        mockReport.setTemperature(25.0);
        mockReport.setHumidity(60);

        when(weatherReportService.getWeatherReport(anyDouble(), anyDouble())).thenReturn(mockReport);

        mockMvc.perform(get("/v1/api/weather-report")
                        .param("latitude", "37.8267")
                        .param("longitude", "-122.4233"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.temperature").value(25.0))
                .andExpect(jsonPath("$.humidity").value(60));
    }

    @Test
    public void testGetWeatherReportWithDifferentCoordinates() throws Exception {
        WeatherReport mockReport = new WeatherReport();
        mockReport.setTemperature(30.0);
        mockReport.setHumidity(70);

        when(weatherReportService.getWeatherReport(anyDouble(), anyDouble())).thenReturn(mockReport);

        mockMvc.perform(get("/v1/api/weather-report")
                        .param("latitude", "40.7128")
                        .param("longitude", "-74.0060"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.temperature").value(30.0))
                .andExpect(jsonPath("$.humidity").value(70));
    }


}
