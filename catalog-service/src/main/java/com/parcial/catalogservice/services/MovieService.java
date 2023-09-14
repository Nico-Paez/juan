package com.parcial.catalogservice.services;

import com.parcial.catalogservice.client.IMovieClient;
import com.parcial.catalogservice.model.MovieRecord;
import com.parcial.catalogservice.models.Movie;
import com.parcial.catalogservice.repositories.MovieRepository;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class MovieService {
    private final MovieRepository repository;

    private final IMovieClient repositoryClient;

    //@CircuitBreaker(name = "movie", fallbackMethod = "movieGenreFallback")
    //@Retry(name = "movie")
    public List<Movie> findByGenre (String genre){
        return repository.findAllByGenre(genre);
    }

    public Movie save (Movie movie){
        return repository.save(movie);
    }

    @CircuitBreaker(name = "movie", fallbackMethod = "movieSaveFallback")
    @Retry(name = "movie")
    public String saveMovie (MovieRecord movie){
        log.info("llamando metodo save de movie");

        return repositoryClient.saveMovie(movie,false);
    }

    private String movieSaveFallback(CallNotPermittedException exception) {
        return "Microservicio Movie no disponible.";
    }










    /*@ExceptionHandler(CallNotPermittedException.class)
    private Object movieGenreFallback (CallNotPermittedException exception){
        return "El microservicio movie está caído.";
    }

    @ExceptionHandler(CallNotPermittedException.class)
    private Object movieSaveFallback (CallNotPermittedException exception){
        return "El microservicio movie está caído.";
    }*/
}