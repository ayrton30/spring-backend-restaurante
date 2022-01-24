package com.coderhouse.app.controller;

import com.coderhouse.app.handler.IdNotFoundException;
import com.coderhouse.app.model.Restaurante;
import com.coderhouse.app.service.RestauranteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/restaurante")
public class RestauranteController {

    private final RestauranteService service;

    @GetMapping("/all")
    public Iterable<Restaurante> readAll() {
        return this.service.getAll();
    }

    @GetMapping("/{id}")
    public Restaurante getById(@PathVariable Long id) throws IdNotFoundException  {
        log.info("Request GET getById:" + id);
        return this.service.getRestauranteById(id);
    }

    @PostMapping("")
    public Restaurante createRestaurante(@RequestBody Restaurante restaurante) {
        log.info("Request POST createRestaurante");
        return this.service.createRestaurante(restaurante);
    }

    @PutMapping("")
    public void updateRestaurante(@RequestBody Restaurante restaurante) throws IdNotFoundException {
        log.info("Request PUT updateRestaurante");
        this.service.updateRestaurante(restaurante.getId(), restaurante);
    }

    @DeleteMapping("/{id}")
    public void deleteRestaurante(@PathVariable Long id) throws IdNotFoundException {
        log.info("Request DELETE deleteRestaurante id->:" + id);
        this.service.deleteRestaurante(id);
    }

    @PostMapping("/stringMap")
    public String createRestaurante(@RequestBody String restaurante) {
        return this.service.createRestauranteMap(restaurante);
    }
}
