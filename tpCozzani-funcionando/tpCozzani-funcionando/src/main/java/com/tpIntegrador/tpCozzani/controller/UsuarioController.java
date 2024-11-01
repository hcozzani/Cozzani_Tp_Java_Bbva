package com.tpIntegrador.tpCozzani.controller;


import com.tpIntegrador.tpCozzani.dtos.CrearUsuarioDTO;
import com.tpIntegrador.tpCozzani.dtos.UsuarioDTO;
import com.tpIntegrador.tpCozzani.service.UsuarioServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioServiceImplementation usuarioServiceImplementation;

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody CrearUsuarioDTO crearUsuarioDTO) {
        usuarioServiceImplementation.crear(crearUsuarioDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> findAll() {
        return ResponseEntity.ok(usuarioServiceImplementation.listar());
    }
}
