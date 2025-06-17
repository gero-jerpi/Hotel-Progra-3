package com.VegaMamaniJerpi.hotel.Usuarios.Servicio;

import com.VegaMamaniJerpi.hotel.Usuarios.Modelo.Usuario;
import com.VegaMamaniJerpi.hotel.Usuarios.Modelo.UsuarioRepositorio;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService{

    private final UsuarioRepositorio repositorio;
    private final PasswordEncoder passwordEncoder;

    public UsuarioServiceImpl(UsuarioRepositorio repositorio, PasswordEncoder passwordEncoder) {
        this.repositorio = repositorio;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public Optional<Usuario> buscarSegunNombre(String nombre) {
        return repositorio.buscarSegunNombre(nombre);
    }

    @Override
    public List<Usuario> listarUsuarios() {
        return repositorio.findAll();
    }

    @Override
    public Usuario guardar(Usuario usuario) {
        usuario.setContrasenia(passwordEncoder.encode(usuario.getContrasenia()));
        return repositorio.save(usuario);
    }


    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        Usuario user = repositorio.buscarSegunNombre(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        return new org.springframework.security.core.userdetails.User(
                user.getNombre(),
                user.getContrasenia(),
                List.of(new SimpleGrantedAuthority(user.getRol().toString()))
        );
    }

}
