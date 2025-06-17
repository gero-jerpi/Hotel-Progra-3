package com.VegaMamaniJerpi.hotel.Config;

import com.VegaMamaniJerpi.hotel.Enums.Rol;
import com.VegaMamaniJerpi.hotel.Usuarios.Modelo.Usuario;
import com.VegaMamaniJerpi.hotel.Usuarios.Servicio.UsuarioServicioImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private UsuarioServicioImpl usuarioServicio;

    @Override
    public void run(String... args) throws Exception {
        if (usuarioServicio.cantidadUsuarios() == 0) {
            Usuario admin = new Usuario();
            admin.setNombre("ADMIN");
            admin.setContrasenia("ADMIN"); // o la clave que quieras
            admin.setRol(Rol.ADMIN);
            // Seteá otros campos necesarios del usuario
            usuarioServicio.guardar(admin); // Esto debería asignar el rol admin automáticamente si usaste lo anterior

            System.out.println("ADMIN CREADO");
        }
    }
}