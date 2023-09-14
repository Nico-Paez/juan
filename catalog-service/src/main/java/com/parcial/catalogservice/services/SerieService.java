package com.parcial.catalogservice.services;

import com.parcial.catalogservice.models.Serie;
import com.parcial.catalogservice.repositories.SerieRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Slf4j
@RequiredArgsConstructor
@Service
public class SerieService {
    private final SerieRepository repository;

    //@CircuitBreaker(name = "serie", fallbackMethod = "serieGenreFallback")
    //@Retry(name = "serie")
    public List<Serie> findByGenre (String genre){
        return repository.findAllByGenre(genre);
    }

    //@CircuitBreaker(name = "serie", fallbackMethod = "serieAllFallback")
    //@Retry(name = "serie")
    //@CircuitBreaker(name = "findAll", fallbackMethod = "findAllEmptyFallback")
    //@Retry(name = "findAll")
    //public  List<Serie> findAll(Boolean throwError){
    public  List<Serie> findAll(){
        //log.info("llamando metodo findAll de series");
        //if (throwError){
        //    throw new RuntimeException();
        //}
        return repository.findAll();
    }

    /*private List<Serie> findAllEmptyFallback(Exception exception) {
        return new ArrayList<>();
    }*/

    //@CircuitBreaker(name = "serie", fallbackMethod = "serieSaveFallback")
    //@Retry(name = "serie")
    public Serie save (Serie serie){
        return repository.save(serie);
    }















    /*@ExceptionHandler(CallNotPermittedException.class)
    private Object serieGenreFallback (CallNotPermittedException exception){
        return "El microservicio serie está caído.";
    }

    @ExceptionHandler(CallNotPermittedException.class)
    private Object serieAllFallback (CallNotPermittedException exception){
        return "El microservicio serie está caído.";
    }

    @ExceptionHandler(CallNotPermittedException.class)
    private Object serieSaveFallback (CallNotPermittedException exception){
        return "El microservicio serie está caído.";
    }*/
}
