package com.ainur.serviciooauth.security.event;

import com.ainur.serviciooauth.services.IUsuarioService;
import com.ainur.usuarios.commons.usuarioscommons.models.Usuario;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

import feign.FeignException;

// class for handle the authentication success and error events

@Component
public class AuthenticationSuccessErrorHandler implements AuthenticationEventPublisher {

    private Logger log = LoggerFactory.getLogger(AuthenticationSuccessErrorHandler.class);

    @Autowired
    private IUsuarioService usuarioService;

    @Override
    public void publishAuthenticationSuccess(Authentication authentication) {

        if ( authentication.getDetails() instanceof WebAuthenticationDetails ) {
            return;
        }
        
        UserDetails user = (UserDetails) authentication.getPrincipal();
        String mensaje = "Success Login: " + user.getUsername();
        System.out.println(mensaje);
        log.info(mensaje);

        Usuario usuario = usuarioService.findByUsername(authentication.getName());

        if(usuario.getIntentos() != null && usuario.getIntentos() > 0) {
            usuario.setIntentos(0);
            usuarioService.update(usuario, usuario.getId());
        }
    }

    @Override
    public void publishAuthenticationFailure(AuthenticationException exception, Authentication authentication) {
        String mensaje = "Error en el Login: " + exception.getMessage();
        System.out.println(mensaje);
        log.info(mensaje);

        try {
            Usuario usuario = usuarioService.findByUsername(authentication.getName());
            if ( usuario.getIntentos() == null ) {
                usuario.setIntentos(0);
            }

            log.info("Intentos actual es de: " + usuario.getIntentos());

            usuario.setIntentos(usuario.getIntentos() + 1);

            log.info("Intentos después es de: " + usuario.getIntentos());

            if(usuario.getIntentos() >= 3) {
                log.error(String.format("El usuario %s des-habilitado por máximos intentos", usuario.getUsername()));
                usuario.setEnabled(false);
            }

            usuarioService.update(usuario, usuario.getId());

        } catch (FeignException e) {
            log.error(String.format("El usuario %s no existe en el sistema", authentication.getName()));
        }
    }
    
}
