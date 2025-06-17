package com.VegaMamaniJerpi.hotel.Seguridad;

import com.VegaMamaniJerpi.hotel.Usuarios.Servicio.UsuarioServicioImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    private final UsuarioServicioImpl usuarioServicio;

    public SecurityConfig(UsuarioServicioImpl usuarioServicio) {
        this.usuarioServicio = usuarioServicio;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // API REST, deshabilitamos CSRF
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST, "/api/auth").hasRole("ADMIN")   // Solo admin puede registrar
                        .requestMatchers(HttpMethod.GET, "/api/auth").hasRole("ADMIN")   // Solo admin puede listar usuarios
                        .anyRequest().authenticated()                                    // Todo lo demás requiere autenticación
                )
                .userDetailsService(usuarioServicio)
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }

}