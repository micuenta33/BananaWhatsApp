package com.banana.bananawhatsapp.persistencia.usuarios;

import com.banana.bananawhatsapp.exceptions.UsuarioException;
import com.banana.bananawhatsapp.modelos.Usuario;
import com.banana.bananawhatsapp.persistencia.usuarios.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;


@Repository
public class UsuarioRepositoryImpl implements IUsuarioRepository {

    @Autowired
    private UsuarioRepositoryMySQL usuarioRepositoryMySQL;

    @Override
    public Usuario obtener(int id) throws SQLException {
        return usuarioRepositoryMySQL.findById(id).orElseThrow(() -> new UsuarioException("Usuario no encontrado"));
    }

    @Override
    public Usuario crear(Usuario usuario) {
        return  usuarioRepositoryMySQL.save(usuario);
    }

    @Override
    public Usuario actualizar(Usuario usuario) {
        if (usuarioRepositoryMySQL.existsById(usuario.getId())){
            return usuarioRepositoryMySQL.save(usuario);
        }
        throw new UsuarioException("Usuario no encontrado");
    }

    @Override
    public boolean borrar(Usuario usuario)  {
        if(usuarioRepositoryMySQL.existsById(usuario.getId())){
            usuarioRepositoryMySQL.delete(usuario);
            return true;
        }
        return false;
    }

}
