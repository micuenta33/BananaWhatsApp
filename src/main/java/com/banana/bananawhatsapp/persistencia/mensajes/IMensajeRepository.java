package com.banana.bananawhatsapp.persistencia.mensajes;

import com.banana.bananawhatsapp.modelos.Mensaje;
import com.banana.bananawhatsapp.modelos.Usuario;

import java.sql.SQLException;
import java.util.List;

public interface IMensajeRepository {
    public Mensaje crear(Mensaje mensaje);

    public boolean borrarEntre(Usuario remitente, Usuario destinatario) throws Exception;


    public List<Mensaje> obtenerEntre(Usuario remitente, Usuario destinatario) ;
}
