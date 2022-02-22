package com.ainur.servicioitem.config;

import java.time.Duration;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;

@Configuration
public class AppConfig {

    // configuración para cada corto circuito, en este caso solo tenemos uno
    // se puede configurar el resilience aqui o en el application.yml
    @Bean
    public Customizer<Resilience4JCircuitBreakerFactory> defaultCustomizer() {
        return factory -> factory.configureDefault(id -> {
            return new Resilience4JConfigBuilder(id)
                .circuitBreakerConfig(CircuitBreakerConfig.custom()
                    // umbral de requests en los que se evaluarán las cantidades de fallidos y correctos
                    .slidingWindowSize(10)
                    // porcentaje de fallidos
                    .failureRateThreshold(50)
                    // tiempo de espera en estado abierto
                    .waitDurationInOpenState(Duration.ofSeconds(10L))
                    // cantidad de requests en estado semiabierto
                    .permittedNumberOfCallsInHalfOpenState(5)
                    // porcentaje del umbral de llamadas lentas
                    .slowCallRateThreshold(50)
                    .slowCallDurationThreshold(Duration.ofSeconds(2L))
                    .build())
                // tiempo de espera a una request antes de lanzar timeout 1 seg por defecto
                // .timeLimiterConfig(TimeLimiterConfig.ofDefaults())
                // tiempo de espera timeout personalizado a 2 seg
                .timeLimiterConfig(TimeLimiterConfig.custom().timeoutDuration(Duration.ofSeconds(6L)).build())
                .build();
        });
    }
    
}
