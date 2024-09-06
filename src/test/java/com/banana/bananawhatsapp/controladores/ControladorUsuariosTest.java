package com.banana.bananawhatsapp.controladores;

import com.banana.bananawhatsapp.config.SpringConfig;
import com.banana.bananawhatsapp.exceptions.UsuarioException;
import com.banana.bananawhatsapp.modelos.Usuario;
import com.banana.bananawhatsapp.persistencia.usuarios.IUsuarioRepository;
import com.banana.bananawhatsapp.util.DBUtil;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpringConfig.class})
@EnableAutoConfiguration
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ControladorUsuariosTest {
    @Autowired
    ControladorUsuarios controladorUsuarios;

    @Autowired
    IUsuarioRepository repoUser;

    @Test
    @Order(1)
    void dadoUsuarioValido_cuandoAlta_entoncesUsuarioValido() {
        Usuario nuevo = new Usuario(null, "Ricardo", "r@r.com", LocalDate.now(), true);
        controladorUsuarios.alta(nuevo);

        assertThat(nuevo, notNullValue());
        assertThat(nuevo.getId(), greaterThan(0));
    }

    @Test
    @Order(2)
    void dadoUsuarioNOValido_cuandoAlta_entoncesExcepcion() {
        Usuario user = new Usuario(null, "Gema", "g@gccom", LocalDate.now(), true);
        assertThrows(Exception.class, () -> {
            controladorUsuarios.alta(user);
        });
    }

    @Test
    @Order(3)
    void dadoUsuarioValido_cuandoActualizar_entoncesUsuarioValido() throws Exception {
        Integer iDUser = 1;
        LocalDate fecha = LocalDate.parse("2023-12-17");
        Usuario user = repoUser.obtener(iDUser);
        user.setNombre("Juan Luis");
        user.setEmail("jl@jll.com");
        user.setAlta(fecha);
        controladorUsuarios.actualizar(user);
        assertThat(repoUser.obtener(iDUser).getNombre(), is("Juan Luis"));
    }

    @Test
    @Order(4)
    void dadoUsuarioNOValido_cuandoActualizar_entoncesExcepcion() {
        assertThrows(UsuarioException.class, () -> {
            Integer iDUser = 2;
            LocalDate fecha = LocalDate.parse("2025-12-17");
            Usuario user = repoUser.obtener(iDUser);
            user.setNombre(null);
            user.setEmail("jl@jll.com");
            user.setAlta(fecha);
            controladorUsuarios.actualizar(user);
        });
    }

    @Test
    @Order(5)
    void dadoUsuarioValido_cuandoBaja_entoncesUsuarioValido() throws Exception {
        Usuario user = repoUser.obtener(1);
        boolean ok = controladorUsuarios.baja(user);
        assertThat(ok, is(true));
    }

    @Test
    @Order(6)
    void dadoUsuarioNOValido_cuandoBaja_entoncesExcepcion() {
        Usuario user = new Usuario(-1, null, null, null, true);
        assertThrows(Exception.class, () -> {
            controladorUsuarios.baja(user);
        });
    }
}