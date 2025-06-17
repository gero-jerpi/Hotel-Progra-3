package com.VegaMamaniJerpi.hotel.Usuarios.Servicio;

import com.VegaMamaniJerpi.hotel.Usuarios.Modelo.Usuario;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    Optional<Usuario> buscarSegunNombre(String nombre);
    List<Usuario> listarUsuarios();
    Usuario guardar(Usuario usuario);
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
