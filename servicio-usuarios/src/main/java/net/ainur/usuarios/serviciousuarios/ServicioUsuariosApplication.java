package net.ainur.usuarios.serviciousuarios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

// comando para escanear la dependencia usuario-commons
@EntityScan({"com.ainur.usuarios.commons.usuarioscommons.models"})
@SpringBootApplication
public class ServicioUsuariosApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServicioUsuariosApplication.class, args);
	}

}
