package com.tpIntegrador.tpCozzani.modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "polizas")
@Entity

public class Poliza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "tipo_seguro_id")
    private TipoSeguro tipoSeguro;

    private LocalDate fechaEmision;
    private LocalDate fechaVencimiento;
    private  String estado; // ej. activa o vencida
    private Float montoAsegurado;
}
