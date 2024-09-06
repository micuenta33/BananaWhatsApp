package com.banana.bananawhatsapp.servicios.impl;

import com.banana.bananawhatsapp.exceptions.UsuarioException;
import com.banana.bananawhatsapp.modelos.Usuario;
import com.banana.bananawhatsapp.persistencia.usuarios.IUsuarioRepository;
import com.banana.bananawhatsapp.servicios.IServicioUsuarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Set;
@Service
public class IServicioUsuariosImpl implements IServicioUsuarios {
    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Override
    public Usuario obtener(int id) throws UsuarioException {
        return null;
    }

    @Override
    @Transactional
    public Usuario crearUsuario(Usuario usuario) throws UsuarioException {
        if (!usuario.valido()){
            throw new UsuarioException("Usuario no valido");
        }
        return usuarioRepository.crear(usuario);
    }

    @Override
    public boolean borrarUsuario(Usuario usuario) throws UsuarioException {
        if(usuarioRepository.borrar(usuario)){
            return true;
        }
        throw new UsuarioException("Usuario no encontrado");
    }

    @Override
    public Usuario actualizarUsuario(Usuario usuario) throws UsuarioException {
        if (!usuario.valido() || usuario.getId()==null){
            throw new UsuarioException("Usuario no valido");
        }
        return usuarioRepository.actualizar(usuario);
    }


}
