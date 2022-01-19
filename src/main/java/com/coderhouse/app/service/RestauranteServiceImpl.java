package com.coderhouse.app.service;

import com.coderhouse.app.model.Restaurante;
import com.coderhouse.app.repository.RestauranteRepository;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class RestauranteServiceImpl implements RestauranteService {

    private final RestauranteRepository repository;
    private final ObjectMapper mapper;

    //config
    @PostConstruct
    private void PostConfig() {
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss"));
    }


    @Override
    public Restaurante createRestaurante(Restaurante restaurante) {
        try {
            //Serializamos el restaurante a JSON
            mapperToString(restaurante);
            mapperToMap(restaurante);
        } catch (JsonProcessingException e) {
            log.error("Error converting restaurant to string", e);
        }
        return this.repository.save(restaurante);
    }

    @Override
    public Iterable<Restaurante> getAll() {
        return this.repository.findAll();
    }

    @Override
    public String createRestauranteMap(String restaurante)  {
        try {
            //deserealizado JSON->Objeto Restaurante
            var restauranteClass = mapper.readValue(restaurante, Restaurante.class);
            //almacenamos en repo
            Restaurante newRest = this.repository.save(restauranteClass);

            var restauranteString = mapper.writeValueAsString(newRest);
            var restauranteMap = mapper.readValue(restauranteString, Map.class);
            //devolver un restaurante serializado a String en formato Map
            return restauranteMap.toString();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return restaurante;
    }

    void mapperToString(Restaurante restaurante) throws JsonProcessingException {
        var restauranteString = mapper.writeValueAsString(restaurante);
        log.info("Restaurante en formato String : {}", restaurante);
    }

    void mapperToMap(Restaurante restaurante) throws JsonProcessingException {
        var restauranteString = mapper.writeValueAsString(restaurante);
        var restauranteMap = mapper.readValue(restauranteString, Map.class);
        log.info("Restaurante en formato de Mapa : {}", restauranteMap.toString());
    }
}
