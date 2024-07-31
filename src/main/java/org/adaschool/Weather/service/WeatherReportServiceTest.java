package org.adaschool.Weather.service;

import org.adaschool.Weather.data.WeatherApiResponse;
import org.adaschool.Weather.data.WeatherReport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class WeatherReportServiceTest {

    @InjectMocks
    private WeatherReportService weatherReportService;

    @Mock
    private RestTemplate restTemplate;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetWeatherReport() {
        // Crear una respuesta simulada de la API
        WeatherApiResponse mockResponse = new WeatherApiResponse();
        WeatherApiResponse.Main main = new WeatherApiResponse.Main();
        main.setTemperature(25.0);
        main.setHumidity(60);
        mockResponse.setMain(main);

        // Configurar el comportamiento del mock de RestTemplate
        when(restTemplate.getForObject(anyString(), WeatherApiResponse.class)).thenReturn(mockResponse);

        // Ejecutar el método a probar
        WeatherReport report = weatherReportService.getWeatherReport(37.8267, -122.4233);

        // Verificar los resultados
        assertEquals(25.0, report.getTemperature());
        assertEquals(60, report.getHumidity());
    }
}
