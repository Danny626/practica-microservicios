package com.ainur.serviciooauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
/* public class ServicioOauthApplication implements CommandLineRunner { */
public class ServicioOauthApplication {

	/* @Autowired
	private BCryptPasswordEncoder passwordEncoder; */
	public static void main(String[] args) {
		SpringApplication.run(ServicioOauthApplication.class, args);
	}

	/* @Override
	public void run(String... args) throws Exception {
		String password = "12345";

		for (int i = 0; i < 4; i++) {
			String passwordBCrypt = this.passwordEncoder.encode(password);
			System.out.println(passwordBCrypt);
		}
		
	} */

}
