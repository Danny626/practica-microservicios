package net.ainur.usuarios.serviciousuarios.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.ainur.usuarios.serviciousuarios.models.Usuario;

public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {
    
    // este es un query method
    // es una consulta por nombre del atributo
    public Usuario findByUsername(String username);
}
