package com.parcial.catalogservice.services;

import com.parcial.catalogservice.client.ISerieClient;
import com.parcial.catalogservice.model.MovieRecord;
import com.parcial.catalogservice.model.SerieRecord;
import com.parcial.catalogservice.models.Serie;
import com.parcial.catalogservice.repositories.SerieRepository;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Slf4j
@RequiredArgsConstructor
@Service
public class SerieService {
    private final SerieRepository repository;

    private final ISerieClient serieClient;

    public List<Serie> findByGenre (String genre){
        return repository.findAllByGenre(genre);
    }


    public  List<Serie> findAll(){
        return repository.findAll();
    }

    public Serie save (Serie serie){
        return repository.save(serie);
    }

    @CircuitBreaker(name = "serie", fallbackMethod = "serieSaveFallback")
    @Retry(name = "serie")
    public String saveSerie (SerieRecord serieRecord){
        return serieClient.create(serieRecord,false);
    }

    private String serieSaveFallback(CallNotPermittedException exception) {
        return "Microservicio serie no disponible.";
    }
}
