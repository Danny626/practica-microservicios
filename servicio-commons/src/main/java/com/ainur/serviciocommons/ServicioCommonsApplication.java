package com.ainur.serviciocommons;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

// agregamos @EnableAutoConfiguration para que no nos pida necesariamente una conexión a BD
// por utilizar JPA
@SpringBootApplication
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class ServicioCommonsApplication {

	// quitamos esta función ya que este proyecto no se ejecutará,
	// si no que funcionará como libreria

/* 	public static void main(String[] args) {
		SpringApplication.run(ServicioCommonsApplication.class, args);
	} */

}
