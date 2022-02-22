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
                    .build())
                .timeLimiterConfig(TimeLimiterConfig.ofDefaults())
                .build();
        });
    }
    
}
