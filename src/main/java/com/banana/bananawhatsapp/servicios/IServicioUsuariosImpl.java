package com.banana.bananawhatsapp.servicios;

import com.banana.bananawhatsapp.exceptions.UsuarioException;
import com.banana.bananawhatsapp.modelos.Usuario;
import com.banana.bananawhatsapp.persistencia.usuarios.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
@Service
public class IServicioUsuariosImpl implements IServicioUsuarios{
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
        return false;
    }

    @Override
    public Usuario actualizarUsuario(Usuario usuario) throws UsuarioException {
        if (!usuario.valido() || usuario.getId()==null){
            throw new UsuarioException("Usuario no valido");
        }
        return usuarioRepository.actualizar(usuario);
    }

    @Override
    public Set<Usuario> obtenerPosiblesDesinatarios(Usuario usuario, int max) throws UsuarioException {
        return null;
    }
}
