package com.coderhouse.app.service;

import com.coderhouse.app.model.Restaurante;

import java.util.List;

public interface RestauranteService {
    public Restaurante createRestaurante(Restaurante restaurante);
    public Iterable<Restaurante> getAll();

    public String createRestauranteMap(String restaurante);
}
