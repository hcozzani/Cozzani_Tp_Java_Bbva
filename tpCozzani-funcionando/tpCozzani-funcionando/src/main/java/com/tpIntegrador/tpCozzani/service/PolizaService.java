package com.tpIntegrador.tpCozzani.service;

import com.tpIntegrador.tpCozzani.dtos.PolizaDTOS;
import com.tpIntegrador.tpCozzani.dtos.PolizaResponseDTOS;
import com.tpIntegrador.tpCozzani.exceptions.ResourceNotFoundException;
import com.tpIntegrador.tpCozzani.modelo.Poliza;
import com.tpIntegrador.tpCozzani.repository.ClienteRepository;
import com.tpIntegrador.tpCozzani.repository.PolizaRepository;
import com.tpIntegrador.tpCozzani.repository.TiposeguroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service

public class PolizaService {
    @Autowired
    private PolizaRepository polizaRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private TiposeguroRepository tiposeguroRepository;

    // metodo para crear una poliza usando dtos
    public PolizaResponseDTOS crearPoliza(PolizaDTOS polizaDTOS){
        Poliza poliza = new Poliza();
        poliza.setCliente(clienteRepository.findById(polizaDTOS.getClienteId())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado")));
        poliza.setTipoSeguro(tiposeguroRepository.findById(polizaDTOS.getTipoSeguroId())
                .orElseThrow(() -> new ResourceNotFoundException("Tipo de seguro no encontrado")));
        poliza.setFechaEmision(polizaDTOS.getFechaEmision());
        poliza.setFechaVencimiento(polizaDTOS.getFechaVencimiento());
        poliza.setEstado(polizaDTOS.getEstado());
        poliza.setMontoAsegurado(polizaDTOS.getMontoAsegurado());

        Poliza nuevaPoliza = polizaRepository.save(poliza);
        return mapToResponseDTO(nuevaPoliza);

    }

    //metodo para listar las polizas
    public List<PolizaResponseDTOS> listarPolizas() {
        return polizaRepository.findAll()
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    //metodo para actualizar poliza existente con dto
    public PolizaResponseDTOS actualizarPoliza(Long id, PolizaDTOS polizaDTOS) {
        return polizaRepository.findById(id)
                .map(poliza -> {
                    poliza.setCliente(clienteRepository.findById(polizaDTOS.getClienteId())
                            .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado")));
                    poliza.setTipoSeguro(tiposeguroRepository.findById(polizaDTOS.getTipoSeguroId())
                            .orElseThrow(() -> new ResourceNotFoundException("Tipo de seguro no encontrado")));
                    poliza.setFechaVencimiento(polizaDTOS.getFechaVencimiento());
                    poliza.setMontoAsegurado(polizaDTOS.getMontoAsegurado());
                    poliza.setEstado(polizaDTOS.getEstado());

                    Poliza polizaActualizada = polizaRepository.save(poliza);
                    return mapToResponseDTO(polizaActualizada);
                }).orElseThrow(() -> new ResourceNotFoundException("Póliza no encontrada"));
    }

    public void eliminarPoliza(Long id) {
        Poliza poliza = polizaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Póliza no encontrada con el ID: " + id));
        polizaRepository.delete(poliza);
    }

    //Metodo auxiliar para convertir Poliza en PolizaResponseDTOS
    private PolizaResponseDTOS mapToResponseDTO(Poliza poliza) {
        PolizaResponseDTOS responseDTO = new PolizaResponseDTOS();
        responseDTO.setId(poliza.getId());
        responseDTO.setClienteId(poliza.getCliente().getClienteId());
        responseDTO.setTipoSeguroId(poliza.getTipoSeguro().getTipoSeguroid());
        responseDTO.setFechaEmision(poliza.getFechaEmision());
        responseDTO.setFechaVencimiento(poliza.getFechaVencimiento());
        responseDTO.setEstado(poliza.getEstado());
        responseDTO.setMontoAsegurado(poliza.getMontoAsegurado());
        return responseDTO;
    }
}
