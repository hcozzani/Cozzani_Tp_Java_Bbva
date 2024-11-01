package com.tpIntegrador.tpCozzani.controller;


import com.tpIntegrador.tpCozzani.service.AuthServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthServiceImplementation authServiceImplementation;

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestHeader final String username,
            @RequestHeader final String password
    ) {
        return ResponseEntity.ok(authServiceImplementation.login(username, password));
    }

    @GetMapping("/hashing")
    public ResponseEntity<String> hashing (@RequestParam String password) {

        return ResponseEntity.ok().body("Contraseña encriptada: " + authServiceImplementation.generarHash(password));
    }
}