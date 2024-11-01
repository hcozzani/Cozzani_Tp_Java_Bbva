package com.tpIntegrador.tpCozzani.dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PolizaResponseDTOS {

    private Long id;
    private Long clienteId;
    private Long tipoSeguroId;
    private LocalDate fechaEmision;
    private LocalDate fechaVencimiento;
    private String estado;
    private Float montoAsegurado;

}
