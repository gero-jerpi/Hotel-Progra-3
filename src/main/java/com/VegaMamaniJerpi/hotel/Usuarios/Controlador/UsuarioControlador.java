package com.VegaMamaniJerpi.hotel.Usuarios.Controlador;

import com.VegaMamaniJerpi.hotel.Usuarios.Modelo.Usuario;
import com.VegaMamaniJerpi.hotel.Usuarios.Servicio.UsuarioServicioImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class UsuarioControlador {

    private final UsuarioServicioImpl servicio;

    public UsuarioControlador(UsuarioServicioImpl servicio) {
        this.servicio = servicio;
    }

    // Registro
    @PostMapping
    public Usuario guardar(@RequestBody Usuario usuario) {
        return servicio.guardar(usuario);
    }

    // Solo con fines de prueba, NO incluir en producción // IMPORTANTE!!!!!!!!!!!!
    @GetMapping
    public List<Usuario> listarUsuarios() {
        return servicio.listarUsuarios();
    }


}
