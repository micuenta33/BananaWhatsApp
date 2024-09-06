package com.banana.bananawhatsapp.persistencia;

import com.banana.bananawhatsapp.config.SpringConfig;
import com.banana.bananawhatsapp.exceptions.UsuarioException;
import com.banana.bananawhatsapp.modelos.Mensaje;
import com.banana.bananawhatsapp.modelos.Usuario;
import com.banana.bananawhatsapp.persistencia.mensajes.IMensajeRepository;
import com.banana.bananawhatsapp.persistencia.usuarios.IUsuarioRepository;
import com.banana.bananawhatsapp.util.DBUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpringConfig.class})
@EnableAutoConfiguration
class MensajeRepositoryTest {

    @Autowired
    IUsuarioRepository repoUsuario;

    @Autowired
    IMensajeRepository repoMensaje;


    @Test
    @Order(1)
    @Transactional
    void dadoUnMensajeValido_cuandoCrear_entoncesMensajeValido() throws Exception {
        Usuario remitente = repoUsuario.obtener(1);
        Usuario destinatario = repoUsuario.obtener(2);

        Mensaje message = new Mensaje(null, remitente, destinatario, "De acuerdo Juana. Un saludo.", LocalDate.now());

        repoMensaje.crear(message);
        assertThat(message, notNullValue());
        assertThat(message.getId(), greaterThan(0));
    }

    @Test
    @Order(2)
    void dadoUnMensajeNOValido_cuandoCrear_entoncesExcepcion() throws Exception {
        Usuario remitente = new Usuario(1, null, null, null, true);
        Usuario destinatario = new Usuario(2, null, null, null, true);
        Mensaje message = new Mensaje(null, destinatario, remitente, "SMS < 10", LocalDate.now());
        assertThrows(Exception.class, () -> {
            repoMensaje.crear(message);
        });
    }

    @Test
    @Order(5)
    void dadoUnRemitenteValido_cuandoBorrarEntre_entoncesOK() throws Exception {
        Usuario remitente = repoUsuario.obtener(1);
        Usuario destinatario = repoUsuario.obtener(2);

        boolean borrarChat = repoMensaje.borrarEntre(remitente, destinatario);
        assertTrue(borrarChat);
    }

    @Test
    @Order(6)
    void dadoUnRemitenteNOValido_cuandoBorrarEntre_entoncesExcepcion() throws Exception {
        Usuario remitente = repoUsuario.obtener(1);
        remitente.setActivo(false);
        Usuario destinatario = repoUsuario.obtener(2);

        assertThrows(UsuarioException.class, () -> {
            repoMensaje.borrarEntre(remitente, destinatario);
        });
    }

}