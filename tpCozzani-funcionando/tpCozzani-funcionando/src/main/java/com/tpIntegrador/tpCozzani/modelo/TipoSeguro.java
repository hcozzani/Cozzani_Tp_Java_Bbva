package com.tpIntegrador.tpCozzani.modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tipos_seguros")
@Entity

public class TipoSeguro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long tipoSeguroid;

    private String nombre;
    private String descripcion;
    private Float primaAnual;
    private String estado;
}
