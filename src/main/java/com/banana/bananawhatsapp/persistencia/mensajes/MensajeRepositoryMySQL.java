package com.banana.bananawhatsapp.persistencia.mensajes;

import com.banana.bananawhatsapp.modelos.Mensaje;
import com.banana.bananawhatsapp.modelos.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MensajeRepositoryMySQL extends JpaRepository<Mensaje, Integer> {
    @Query("SELECT m FROM Mensaje m WHERE m.remitente = ?1 AND m.destinatario =?2")
    List<Mensaje> findByRemitenteAndDestinatario(@Param("remitente") Usuario remitente, @Param("destinatario")Usuario destinatario);
}
