package com.parcial.catalogservice.services;


import com.parcial.catalogservice.model.SerieRecord;
import com.parcial.catalogservice.models.Serie;
import com.parcial.catalogservice.repositories.SerieRepository;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SerieService {
    private final SerieRepository repository;

    @CircuitBreaker(name = "serie", fallbackMethod = "serieFallback")
    @Retry(name = "serie")
    public List<Serie> findByGenre (String genre){
        return repository.findAllByGenre(genre);
    }

    @CircuitBreaker(name = "serie", fallbackMethod = "serieFallback")
    @Retry(name = "serie")
    public Serie save (Serie serie){
        return repository.save(serie);
    }

    private Object serieFallback(CallNotPermittedException exception){
        return "El microservicio serie está caído.";
    }
}
