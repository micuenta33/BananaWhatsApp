package com.banana.bananawhatsapp.persistencia.usuarios;

import com.banana.bananawhatsapp.modelos.Usuario;

import java.sql.SQLException;
import java.util.Set;

public interface IUsuarioRepository {
    public Usuario obtener(int id) throws SQLException;
    public Usuario crear(Usuario usuario);

    public Usuario actualizar(Usuario usuario);

    public boolean borrar(Usuario usuario) ;


}
