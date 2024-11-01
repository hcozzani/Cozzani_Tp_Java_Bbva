package com.tpIntegrador.tpCozzani.service;


import com.tpIntegrador.tpCozzani.dtos.CrearUsuarioDTO;
import com.tpIntegrador.tpCozzani.dtos.UsuarioDTO;
import com.tpIntegrador.tpCozzani.modelo.Usuario;
import com.tpIntegrador.tpCozzani.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImplementation {

    @Autowired
    private UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    //1- Obtener Usuario por username.
    public Usuario getByUsername(final String username) {
        return usuarioRepository.findByUsername(username);
    }

    //2- Obtener lista de Usuarios.
    public List<UsuarioDTO> listar() {
        List<Usuario> list = usuarioRepository.findAll();
        return list.stream().map(usuario ->
                new UsuarioDTO().fromUsuario(usuario)
        ).toList();
    }

    //3- Crear Usuario.
    public void crear(final CrearUsuarioDTO crearUsuarioDTO) {
        Usuario usuario = new Usuario();
        usuario.setUsername(crearUsuarioDTO.getUsername());
        usuario.setActivo(Boolean.TRUE);
        usuario.setPassword(passwordEncoder.encode(crearUsuarioDTO.getPassword()));
        usuario.setRoles(crearUsuarioDTO.getRoles());
        usuarioRepository.save(usuario);
    }

}