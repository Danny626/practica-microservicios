package com.ainur.serviciooauth.services;

import com.ainur.usuarios.commons.usuarioscommons.models.Usuario;

public interface IUsuarioService {

    public Usuario findByUsername(String username);

    public Usuario update(Usuario usuario, Long id);
    
}
