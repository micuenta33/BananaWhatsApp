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
    public Mensaje crear(Mensaje mensaje) throws SQLException {
        return repository.save(mensaje);
    }

    @Override
    public List<Mensaje> obtener(Usuario usuario) throws SQLException {
//        List<Mensaje> mensaje = repository.findByRemitenteOrDestinatario(usuario);
        return null;
    }

    @Override
    public boolean borrarEntre(Usuario remitente, Usuario destinatario) throws Exception {
        return false;
    }

    @Override
    public boolean borrarTodos(Usuario usuario) throws SQLException {
        return false;
    }
}
