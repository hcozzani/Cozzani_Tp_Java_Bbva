package com.tpIntegrador.tpCozzani.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class CrearUsuarioDTO {

    private String username;
    private String password;
    private String roles;
}