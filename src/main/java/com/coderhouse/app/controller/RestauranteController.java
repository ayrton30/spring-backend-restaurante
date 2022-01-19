package com.coderhouse.app.controller;

import com.coderhouse.app.model.Restaurante;
import com.coderhouse.app.service.RestauranteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/restaurante")
public class RestauranteController {

    private final RestauranteService service;

    @GetMapping("/all")
    public Iterable<Restaurante> readAll() {
        return this.service.getAll();
    }

    @PostMapping("")
    public Restaurante createRestaurante(@RequestBody Restaurante restaurante) {
        return this.service.createRestaurante(restaurante);
    }

    @PostMapping("/stringMap")
    public String createRestaurante(@RequestBody String restaurante) {
        return this.service.createRestauranteMap(restaurante);
    }
}
