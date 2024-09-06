package com.banana.bananawhatsapp.persistencia.mensajes;

import com.banana.bananawhatsapp.modelos.Mensaje;
import com.banana.bananawhatsapp.modelos.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Repository
public class MensajeRepositoryImpl implements IMensajeRepository {

    @Autowired
    private MensajeRepositoryMySQL repository;

    @Override
    public Mensaje crear(Mensaje mensaje) {
        return repository.save(mensaje);
    }


    @Override
    public boolean borrarEntre(Usuario remitente, Usuario destinatario) throws Exception {
        List<Mensaje> mensajesList = repository.findByRemitenteAndDestinatario(remitente, destinatario);
        if (mensajesList.size() > 0) {
            repository.deleteAll(mensajesList);
            return true;
        }
        return false;
    }

    @Override
    public List<Mensaje> obtenerEntre(Usuario remitente, Usuario destinatario) {
        return repository.findByRemitenteAndDestinatario(remitente, destinatario);
    }
}
