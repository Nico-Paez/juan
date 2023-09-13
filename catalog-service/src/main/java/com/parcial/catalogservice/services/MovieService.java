package com.parcial.catalogservice.services;

import com.parcial.catalogservice.model.Movie;
import com.parcial.catalogservice.repositories.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MovieService {
    private final MovieRepository repository;

    public List<Movie> findByGenre (String genre){
        return repository.findAllByGenre(genre);
    }

    public Movie save (Movie movie){ return repository.save(movie);}


}
