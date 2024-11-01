package com.tpIntegrador.tpCozzani.service;


import com.tpIntegrador.tpCozzani.config.JwtService;
import com.tpIntegrador.tpCozzani.exceptions.ExceptionsSeguridad;
import com.tpIntegrador.tpCozzani.modelo.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthServiceImplementation {

    private final AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UsuarioServiceImplementation usuarioService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public Map<String, Object> login(final String username, final String password) {
        Map<String, Object> response = new HashMap<>();
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            Usuario usuario = usuarioService.getByUsername(username);
            response.put("id", usuario.getId());
            response.put("token", jwtService.generateToken(usuario.getId(), username, usuario.getRoles()));
        } catch (Exception e) {
            throw new ExceptionsSeguridad(HttpStatus.UNAUTHORIZED, "Credenciales invalidas....");
        }
        return response;
    }

    public String generarHash(final String password) {

        return bCryptPasswordEncoder.encode(password);
    }
}