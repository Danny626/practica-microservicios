package com.ainur.servicioitem.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import com.ainur.servicioitem.models.Item;
import com.ainur.serviciocommons.models.Producto;
import com.ainur.servicioitem.service.IItemService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;

@RefreshScope
@RestController
public class ItemController {

    private final Logger logger = LoggerFactory.getLogger(ItemController.class);

    @Autowired
    private Environment env;

    @Value("${configuracion.texto}")
    private String texto;

    @Autowired
    private CircuitBreakerFactory cbFactory;

    @Autowired
    @Qualifier("serviceFeign")
    private IItemService itemService;

    @GetMapping("/listar")
    public List<Item> listar(@RequestParam(name = "nombre", required = false) String nombre, @RequestHeader(name = "token-request", required = false) String token) {
        return this.itemService.findAll();
    }

    @GetMapping("/ver/{id}/cantidad/{cantidad}")
    public Item detalle(@PathVariable Long id, @PathVariable Integer cantidad) {
        return this.cbFactory.create("items")
                .run(() -> this.itemService.findById(id, cantidad), e -> metodoAlternativo(id, cantidad, e));
    }

    // utilizamos anotaciones para utilizar circuitBreaker
    // ya no utilizamos el cbFactory para crear el circuitBreaker por tanto 
    // la configuración q debemos utilizar es el del application.yml
    @CircuitBreaker(name = "items", fallbackMethod = "metodoAlternativo")
    @GetMapping("/ver2/{id}/cantidad/{cantidad}")
    public Item detalle2(@PathVariable Long id, @PathVariable Integer cantidad) {
        return this.itemService.findById(id, cantidad);
    }

    // con TimeLimiter controlamos el circuitBreaker de timeout
    // CompletableFuture es para manejo de llamadas asincronas
    @CircuitBreaker(name = "items", fallbackMethod = "metodoAlternativo2")
    @TimeLimiter(name = "items")
    @GetMapping("/ver3/{id}/cantidad/{cantidad}")
    public CompletableFuture<Item> detalle3(@PathVariable Long id, @PathVariable Integer cantidad) {
        return CompletableFuture.supplyAsync(() -> this.itemService.findById(id, cantidad));
    }

    @GetMapping("/obtener-config")
    public ResponseEntity<?> obtenerConfig(@Value("${server.port}") String puerto) {

        logger.info(this.texto);
        
        Map<String, String> json = new HashMap<>();
        json.put("texto", this.texto);
        json.put("puerto", puerto);

        if( this.env.getActiveProfiles().length > 0 && this.env.getActiveProfiles()[0].equals("dev") ) {
            json.put("autor.nombre", this.env.getProperty("configuracion.autor.nombre"));
            json.put("autor.email", this.env.getProperty("configuracion.autor.email"));
        }

        return new ResponseEntity<Map<String, String>>(json, HttpStatus.OK);
    }

    @PostMapping("/crear")
    @ResponseStatus(HttpStatus.CREATED)
    public Producto crear(@RequestBody Producto producto) {
        return this.itemService.save(producto);
    }

    @PutMapping("/editar/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Producto editar(@RequestBody Producto producto, @PathVariable Long id) {
        
        return this.itemService.update(producto, id);
    }

    @PutMapping("/eliminar/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        
        this.itemService.delete(id);
    }

    public Item metodoAlternativo(Long id, Integer cantidad, Throwable e) {
        logger.info(e.getMessage());
        Item item = new Item();
        Producto producto = new Producto();

        item.setCantidad(cantidad);
        producto.setId(id);
        producto.setNombre("Cámara Sony");
        producto.setPrecio(500.00);
        item.setProducto(producto);

        return item;
    }

    public CompletableFuture<Item> metodoAlternativo2(Long id, Integer cantidad, Throwable e) {
        logger.info(e.getMessage());
        Item item = new Item();
        Producto producto = new Producto();

        item.setCantidad(cantidad);
        producto.setId(id);
        producto.setNombre("Cámara Sony");
        producto.setPrecio(500.00);
        item.setProducto(producto);

        return CompletableFuture.supplyAsync(() -> item);
    }
    
}
