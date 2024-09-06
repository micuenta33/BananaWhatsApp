package com.banana.bananawhatsapp.servicios;

import com.banana.bananawhatsapp.exceptions.MensajeException;
import com.banana.bananawhatsapp.exceptions.UsuarioException;
import com.banana.bananawhatsapp.modelos.Mensaje;
import com.banana.bananawhatsapp.modelos.Usuario;
import com.banana.bananawhatsapp.persistencia.mensajes.IMensajeRepository;
import com.banana.bananawhatsapp.persistencia.usuarios.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Service
public class IServicioMensajeriaImpl implements IServicioMensajeria  {
    @Autowired
    private IMensajeRepository mensajeRepository;
    @Autowired
    private IUsuarioRepository usuarioRepository;


    @Override
    @Transactional
    public Mensaje enviarMensaje(Usuario remitente, Usuario destinatario, String texto) throws UsuarioException, MensajeException {
        Mensaje mensaje = new Mensaje();
        try {
            mensaje.setRemitente(usuarioRepository.obtener(remitente.getId()));
            mensaje.setDestinatario(usuarioRepository.obtener(destinatario.getId()));
            mensaje.setCuerpo(texto);
            mensaje.setFecha(LocalDate.now());
            if (mensaje.valido()){
                 return mensajeRepository.crear(mensaje);
            }
        } catch (Exception e) {
            throw new UsuarioException(e.getMessage());
        }

       throw new MensajeException("Mensaje invalido");
    }

    @Override
    public List<Mensaje> mostrarChatConUsuario(Usuario remitente, Usuario destinatario) throws UsuarioException, MensajeException {
        try {
            if (usuarioRepository.obtener(remitente.getId()).equals(usuarioRepository.obtener(destinatario.getId()))){
                throw new MensajeException("No se puede mostrar el chat con uno mismo");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return mensajeRepository.obtenerEntre(remitente, destinatario);
    }

    @Override
    public boolean borrarChatConUsuario(Usuario remitente, Usuario destinatario) throws UsuarioException, MensajeException {
        return false;
    }
}
