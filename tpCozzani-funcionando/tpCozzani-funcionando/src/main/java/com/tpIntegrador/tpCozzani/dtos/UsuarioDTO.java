package com.tpIntegrador.tpCozzani.dtos;


import com.tpIntegrador.tpCozzani.modelo.Usuario;
import lombok.*;

import java.util.Arrays;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor

public class UsuarioDTO {

    private Long id;
    private String username;
    private Boolean activo;
    private List<String> roles;

    public UsuarioDTO fromUsuario(final Usuario usuario) {
        this.id = usuario.getId();
        this.username = usuario.getUsername();
        this.activo = usuario.getActivo();
        this.roles = Arrays.asList(usuario.getRoles().split(","));
        return this;
    }
}
