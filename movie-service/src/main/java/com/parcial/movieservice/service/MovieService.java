package com.parcial.movieservice.service;


import com.parcial.movieservice.model.Movie;
import com.parcial.movieservice.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author vaninagodoy
 */

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> findByGenre(String genre) {
        return movieRepository.findByGenre(genre);
    }

    public Movie save(Movie movie, Boolean throwError) {
        if (throwError)
            throw new RuntimeException();

        return movieRepository.save(movie);
    }
}
