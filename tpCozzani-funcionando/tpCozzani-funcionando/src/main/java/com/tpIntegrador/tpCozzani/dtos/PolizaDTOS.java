package com.tpIntegrador.tpCozzani.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDate;
@Data

public class PolizaDTOS {
    @NotNull(message = "El ID del cliente es obligatorio")
    private Long clienteId;
    @NotNull(message = "El ID del tipo de seguro es obligatorio")
    private Long tipoSeguroId;
    @NotNull(message = "La fecha de emisión es obligatoria")
    private LocalDate fechaEmision;
    @NotNull(message = "La fecha de vencimiento es obligatoria")
    private LocalDate fechaVencimiento;
    @NotBlank(message = "El estado es obligatorio")
    @Pattern(regexp = "activa|vencida", message = "El estado debe ser 'ACTIVA' o 'VENCIDA'")
    private String estado;
    @NotNull(message = "El monto asegurado es obligatorio")
    @Min(value = 0, message = "El monto asegurado debe ser mayor o igual a 0")
    private Float montoAsegurado;
}
