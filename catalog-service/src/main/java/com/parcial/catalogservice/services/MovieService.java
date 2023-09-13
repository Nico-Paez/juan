package com.parcial.catalogservice.services;

import com.parcial.catalogservice.models.Movie;
import com.parcial.catalogservice.repositories.MovieRepository;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class MovieService {
    private final MovieRepository repository;

    @CircuitBreaker(name = "movie", fallbackMethod = "movieFallback")
    @Retry(name = "movie")
    public List<Movie> findByGenre (String genre){
        log.info("llamando metodo movie por genero");
        return repository.findAllByGenre(genre);
    }

    @CircuitBreaker(name = "movie", fallbackMethod = "movieFallback")
    @Retry(name = "movie")
    public Movie save (Movie movie){
        return repository.save(movie);
    }

    private Object movieFallback(CallNotPermittedException exception){
        return "El microservicio movie está caído.";
    }
}