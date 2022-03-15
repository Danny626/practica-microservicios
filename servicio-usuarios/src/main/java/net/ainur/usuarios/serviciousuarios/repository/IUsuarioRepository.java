package net.ainur.usuarios.serviciousuarios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import net.ainur.usuarios.serviciousuarios.models.Usuario;

@RepositoryRestResource(path = "usuarios")
public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {
    
    // este es un query method findByUsername
    // es una consulta por nombre del atributo
    // @RestResource personaliza el nombre del método expuesto
    // la exposición tb funciona con métodos @Query
    @RestResource(path = "buscar-username")
    public Usuario findByUsername(@Param("nombreUsuario") String username);
}
