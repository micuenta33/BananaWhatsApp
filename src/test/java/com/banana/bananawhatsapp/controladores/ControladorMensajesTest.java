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

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpringConfig.class})
@EnableAutoConfiguration
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ControladorMensajesTest {

    @Autowired
    ControladorMensajes controladorMensajes;

    @Autowired
    IUsuarioRepository usuarioRepository;

    @BeforeEach
    void init() {
        Usuario nuevo2 = new Usuario(null, "usuario1", "usuario1@r.com", LocalDate.now(), true);
        Usuario nuevo1 = new Usuario(null, "usuario2", "usuario2@r.com", LocalDate.now(), true);
        usuarioRepository.crear(nuevo1);
        usuarioRepository.crear(nuevo2);
    }
    @Test
    @Order(1)
    void dadoRemitenteYDestinatarioYTextoValidos_cuandoEnviarMensaje_entoncesOK() {
        Integer remitente = 1;
        Integer destinatario = 2;
        String texto = "Perfecto! Muchas gracias!";
        boolean sendMessage = controladorMensajes.enviarMensaje(remitente, destinatario, texto);
        assertTrue(sendMessage);
    }

    @Test
    @Order(2)
    void dadoRemitenteYDestinatarioYTextoNOValidos_cuandoEnviarMensaje_entoncesExcepcion() {
        Integer remitente = 1;
        Integer destinatario = 2;
        String texto = "SMS < 10";
        assertThrows(Exception.class, () -> {
            controladorMensajes.enviarMensaje(remitente, destinatario, texto);
        });
    }

    @Test
    @Order(3)
    void dadoRemitenteYDestinatarioValidos_cuandoMostrarChat_entoncesOK() {
        Integer remitente = 1;
        Integer destinatario = 2;

        boolean mostrarChat = controladorMensajes.mostrarChat(remitente, destinatario);

        assertTrue(mostrarChat);
    }

    @Test
    @Order(4)
    void dadoRemitenteYDestinatarioNOValidos_cuandoMostrarChat_entoncesExcepcion() {
        Integer remitente = 2;
        Integer destinatario = -1;
        assertThrows(UsuarioException.class, () -> {
            controladorMensajes.mostrarChat(remitente, destinatario);
        });
    }

    @Test
    @Order(5)
    void dadoRemitenteYDestinatarioValidos_cuandoEliminarChatConUsuario_entoncesOK() {
        Integer remitente = 1;
        Integer destinatario = 2;
        boolean eliminarChat = controladorMensajes.eliminarChatConUsuario(remitente, destinatario);
        assertTrue(eliminarChat);
    }

    @Test
    @Order(6)
    void dadoRemitenteYDestinatarioNOValidos_cuandoEliminarChatConUsuario_entoncesExcepcion() {
        Integer remitente = -1;
        Integer destinatario = 5;
        assertThrows(Exception.class, () -> {
            controladorMensajes.eliminarChatConUsuario(remitente, destinatario);
        });
    }
}