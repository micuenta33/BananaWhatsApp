package com.banana.bananawhatsapp.persistencia.usuarios;

import com.banana.bananawhatsapp.modelos.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepositoryMySQL extends JpaRepository<Usuario, Integer> {
}
