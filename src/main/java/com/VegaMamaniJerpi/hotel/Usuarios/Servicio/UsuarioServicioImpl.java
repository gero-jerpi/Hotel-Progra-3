package com.VegaMamaniJerpi.hotel.Usuarios.Servicio;

import com.VegaMamaniJerpi.hotel.Enums.Rol;
import com.VegaMamaniJerpi.hotel.Excepciones.UsuarioYaCargadoException;
import com.VegaMamaniJerpi.hotel.Usuarios.Modelo.Usuario;
import com.VegaMamaniJerpi.hotel.Usuarios.Modelo.UsuarioRepositorio;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServicioImpl implements UsuarioServicio, UserDetailsService {

    private final UsuarioRepositorio repositorio;
    private final PasswordEncoder passwordEncoder;

    public UsuarioServicioImpl(UsuarioRepositorio repositorio, PasswordEncoder passwordEncoder) {
        this.repositorio = repositorio;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public Optional<Usuario> buscarSegunNombre(String nombre) {
        return repositorio.findByNombre(nombre);
    }

    @Override
    public List<Usuario> listarUsuarios() {
        return repositorio.findAll();
    }

    @Override
    public Usuario guardar(Usuario usuario) throws UsuarioYaCargadoException{
        if(repositorio.existsByNombre(usuario.getNombre())){
            throw new UsuarioYaCargadoException("El username ingresado ya está siendo utilizado");
        }

        usuario.setContrasenia(passwordEncoder.encode(usuario.getContrasenia()));

        // Asignar rol según la cantidad de usuarios en la base
        if (repositorio.count() == 0) {
            // Primer usuario -> admin
            usuario.setRol(Rol.ADMIN);
        } else {
            // Usuarios siguientes -> user
            usuario.setRol(Rol.RECEPCIONISTA);
        }

        return repositorio.save(usuario);
    }


    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        Usuario user = repositorio.findByNombre(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        return new org.springframework.security.core.userdetails.User(
                user.getNombre(),
                user.getContrasenia(),
                List.of(new SimpleGrantedAuthority("ROLE_" + user.getRol().toString()))
        );
    }

    // En UsuarioServicio
    public long cantidadUsuarios() {
        return repositorio.count();
    }

}
