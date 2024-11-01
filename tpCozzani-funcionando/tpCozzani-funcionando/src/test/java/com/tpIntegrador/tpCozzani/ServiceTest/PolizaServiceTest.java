package com.tpIntegrador.tpCozzani.ServiceTest;

import com.tpIntegrador.tpCozzani.dtos.PolizaDTOS;
import com.tpIntegrador.tpCozzani.dtos.PolizaResponseDTOS;
import com.tpIntegrador.tpCozzani.exceptions.ResourceNotFoundException;
import com.tpIntegrador.tpCozzani.modelo.Cliente;
import com.tpIntegrador.tpCozzani.modelo.Poliza;
import com.tpIntegrador.tpCozzani.modelo.TipoSeguro;
import com.tpIntegrador.tpCozzani.repository.ClienteRepository;
import com.tpIntegrador.tpCozzani.repository.PolizaRepository;
import com.tpIntegrador.tpCozzani.repository.TiposeguroRepository;
import com.tpIntegrador.tpCozzani.service.PolizaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PolizaServiceTest {

    @InjectMocks
    private PolizaService polizaService;

    @Mock
    private PolizaRepository polizaRepository;

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private TiposeguroRepository tiposeguroRepository;

    private Poliza poliza;
    private PolizaDTOS polizaDTOS;
    private PolizaResponseDTOS polizaResponseDTOS;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Inicializando objetos
        poliza = new Poliza();
        poliza.setId(1L);
        poliza.setFechaEmision(LocalDate.now());
        poliza.setFechaVencimiento(LocalDate.now().plusYears(1));
        poliza.setEstado("activo");
        poliza.setMontoAsegurado(10000.0f);

        polizaDTOS = new PolizaDTOS();
        polizaDTOS.setClienteId(1L);
        polizaDTOS.setTipoSeguroId(1L);
        polizaDTOS.setFechaEmision(LocalDate.now());
        polizaDTOS.setFechaVencimiento(LocalDate.now().plusYears(1));
        polizaDTOS.setEstado("activo");
        polizaDTOS.setMontoAsegurado(10000.00f);

        polizaResponseDTOS = new PolizaResponseDTOS();
        polizaResponseDTOS.setId(1L);
        polizaResponseDTOS.setClienteId(1L);
        polizaResponseDTOS.setTipoSeguroId(1L);
        polizaResponseDTOS.setFechaEmision(LocalDate.now());
        polizaResponseDTOS.setFechaVencimiento(LocalDate.now().plusYears(1));
        polizaResponseDTOS.setEstado("activo");
        polizaResponseDTOS.setMontoAsegurado(10000.00f);
    }

    @Test
    void testCrearPoliza() {
        // Crear un cliente y un tipo de seguro simulados
        Cliente cliente = new Cliente();
        cliente.setClienteId(1L); // Asigna un ID de cliente

        TipoSeguro tipoSeguro = new TipoSeguro();
        tipoSeguro.setTipoSeguroid(1L); // Asigna un ID de tipo de seguro

        // Configurar el comportamiento de los mocks
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
        when(tiposeguroRepository.findById(1L)).thenReturn(Optional.of(tipoSeguro));
        when(polizaRepository.save(any(Poliza.class))).thenAnswer(invocation -> {
            // Almacena la póliza con el cliente y tipo de seguro
            Poliza polizaGuardada = invocation.getArgument(0);
            polizaGuardada.setId(1L); // Asigna un ID para la respuesta
            return polizaGuardada;
        });

        // Ejecutar el método a probar
        PolizaResponseDTOS response = polizaService.crearPoliza(polizaDTOS);

        // Validaciones
        assertNotNull(response);
        assertEquals(1L, response.getId()); // Verifica que el ID sea el esperado
        assertEquals(cliente.getClienteId(), response.getClienteId()); // Verifica el ID del cliente
        assertEquals(tipoSeguro.getTipoSeguroid(), response.getTipoSeguroId()); // Verifica el ID del tipo de seguro
        verify(polizaRepository, times(1)).save(any(Poliza.class));
    }

    @Test
    void testListarPolizas() {
        // Crear un cliente y asignarlo a la póliza
        Cliente cliente = new Cliente();
        cliente.setClienteId(1L); // Asigna un ID de cliente

        // Crear un tipo de seguro y asignarlo a la póliza
        TipoSeguro tipoSeguro = new TipoSeguro();
        tipoSeguro.setTipoSeguroid(1L); // Asigna un ID de tipo de seguro

        // Asignar el cliente y tipo de seguro a la póliza
        poliza.setCliente(cliente);
        poliza.setTipoSeguro(tipoSeguro);

        // Configurar el comportamiento del mock para devolver la póliza
        when(polizaRepository.findAll()).thenReturn(List.of(poliza));

        // Ejecutar el método a probar
        List<PolizaResponseDTOS> responseList = polizaService.listarPolizas();

        // Validaciones
        assertNotNull(responseList);
        assertEquals(1, responseList.size());
        assertEquals(poliza.getId(), responseList.get(0).getId());
        assertEquals(cliente.getClienteId(), responseList.get(0).getClienteId()); // Verifica el cliente ID
        assertEquals(tipoSeguro.getTipoSeguroid(), responseList.get(0).getTipoSeguroId()); // Verifica el tipo de seguro ID
    }

    @Test
    void testActualizarPoliza() {
        when(polizaRepository.findById(1L)).thenReturn(Optional.of(poliza));
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(new Cliente()));
        when(tiposeguroRepository.findById(1L)).thenReturn(Optional.of(new TipoSeguro()));
        when(polizaRepository.save(any(Poliza.class))).thenReturn(poliza);

        PolizaResponseDTOS updatedPoliza = polizaService.actualizarPoliza(1L, polizaDTOS);

        assertNotNull(updatedPoliza);
        assertEquals(poliza.getId(), updatedPoliza.getId());
        verify(polizaRepository, times(1)).save(any(Poliza.class));
    }

    @Test
    void testEliminarPoliza() {
        when(polizaRepository.findById(1L)).thenReturn(Optional.of(poliza));

        assertDoesNotThrow(() -> polizaService.eliminarPoliza(1L));
        verify(polizaRepository, times(1)).delete(poliza);
    }

    @Test
    void testCrearPoliza_ClienteNoEncontrado() {
        when(clienteRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            polizaService.crearPoliza(polizaDTOS);
        });
    }
}