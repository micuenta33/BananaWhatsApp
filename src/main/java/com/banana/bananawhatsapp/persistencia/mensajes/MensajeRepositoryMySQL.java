package com.banana.bananawhatsapp.persistencia.mensajes;

import com.banana.bananawhatsapp.modelos.Mensaje;
import com.banana.bananawhatsapp.modelos.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MensajeRepositoryMySQL extends JpaRepository<Mensaje, Integer> {

//    List<Mensaje> findByRemitenteOrDestinatario(Usuario usuario);
}
