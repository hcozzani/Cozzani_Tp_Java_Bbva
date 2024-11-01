package com.tpIntegrador.tpCozzani.ControllerTest;

import com.tpIntegrador.tpCozzani.controller.PolizaController;
import com.tpIntegrador.tpCozzani.dtos.PolizaDTOS;
import com.tpIntegrador.tpCozzani.dtos.PolizaResponseDTOS;
import com.tpIntegrador.tpCozzani.modelo.Cliente;
import com.tpIntegrador.tpCozzani.modelo.Poliza;
import com.tpIntegrador.tpCozzani.modelo.TipoSeguro;
import com.tpIntegrador.tpCozzani.service.PolizaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import com.tpIntegrador.tpCozzani.dtos.PolizaResponseDTOS;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class PolizaControllerTest {

    @InjectMocks
    private PolizaController polizaController;

    @Mock
    private PolizaService polizaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    void testGetPolizaById() {
        // Configurar el comportamiento del mock
        Cliente cliente = new Cliente();
        TipoSeguro tipoSeguro = new TipoSeguro();
        PolizaResponseDTOS polizaDto = new PolizaResponseDTOS();
        polizaDto.setId(1L);
        polizaDto.setClienteId(1L);
        polizaDto.setTipoSeguroId(1L);
        polizaDto.setFechaEmision(LocalDate.now());
        polizaDto.setFechaVencimiento(LocalDate.now().plusYears(1));
        polizaDto.setEstado("activa");
        polizaDto.setMontoAsegurado(1500.0f);

        List<PolizaResponseDTOS> polizas = Collections.singletonList(polizaDto);

        when(polizaService.listarPolizas()).thenReturn(polizas);

        // llamar al metodo del controlador
        List<PolizaResponseDTOS> response = polizaController.listarPolizas();

        // Verificar la respuesta
        assertEquals(polizas, response);
    }


    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void testCrearPoliza() {
        // Crear una instancia de PolizaDTOS y establecer sus propiedades
        PolizaDTOS polizaDTOS = new PolizaDTOS();
        polizaDTOS.setClienteId(1L);
        polizaDTOS.setTipoSeguroId(1L);
        polizaDTOS.setFechaEmision(LocalDate.now());
        polizaDTOS.setFechaVencimiento(LocalDate.now().plusYears(1));
        polizaDTOS.setEstado("activa");
        polizaDTOS.setMontoAsegurado(1500.0f);

        // Configurar el comportamiento del mock para el servicio
        PolizaResponseDTOS polizaResponse = new PolizaResponseDTOS();
        polizaResponse.setId(1L);
        polizaResponse.setClienteId(1L);
        polizaResponse.setTipoSeguroId(1L);
        polizaResponse.setFechaEmision(LocalDate.now());
        polizaResponse.setFechaVencimiento(LocalDate.now().plusYears(1));
        polizaResponse.setEstado("activa");
        polizaResponse.setMontoAsegurado(1500.0f);

        when(polizaService.crearPoliza(any(PolizaDTOS.class))).thenReturn(polizaResponse);

        // Llamar al método del controlador
        ResponseEntity<PolizaResponseDTOS> response = polizaController.crearPoliza(polizaDTOS);

        // Verificar la respuesta
        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(polizaResponse, response.getBody()); // Comparar solo el cuerpo
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void testActualizarPoliza() {
        // Crear una instancia de PolizaDTOS y establecer sus propiedades
        PolizaDTOS polizaRequest = new PolizaDTOS();
        polizaRequest.setClienteId(1L);
        polizaRequest.setTipoSeguroId(1L);
        polizaRequest.setFechaEmision(LocalDate.now());
        polizaRequest.setFechaVencimiento(LocalDate.now().plusYears(1));
        polizaRequest.setEstado("actualizada");
        polizaRequest.setMontoAsegurado(2000.0f);

        // Crear una instancia de PolizaResponseDTOS para la respuesta simulada
        PolizaResponseDTOS polizaResponse = new PolizaResponseDTOS();
        polizaResponse.setId(1L);
        polizaResponse.setClienteId(1L);
        polizaResponse.setTipoSeguroId(1L);
        polizaResponse.setFechaEmision(LocalDate.now());
        polizaResponse.setFechaVencimiento(LocalDate.now().plusYears(1));
        polizaResponse.setEstado("actualizada");
        polizaResponse.setMontoAsegurado(2000.0f);

        // Configurar el comportamiento del mock
        when(polizaService.actualizarPoliza(eq(1L), any(PolizaDTOS.class))).thenReturn(polizaResponse);

        // Llamar al método del controlador
        ResponseEntity<PolizaResponseDTOS> response = polizaController.actualizarPoliza(1L, polizaRequest);

        // Verificar la respuesta
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(polizaResponse, response.getBody());
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void testEliminarPoliza() {
        // Llamar al método del controlador (sin capturar el resultado)
        polizaController.eliminarPoliza(1L);

        // Verificar que se llamó al servicio con el ID correcto
        verify(polizaService, times(1)).eliminarPoliza(1L);
    }
}