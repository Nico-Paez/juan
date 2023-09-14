package com.parcial.movieservice.controller;

import com.parcial.movieservice.model.Movie;
import com.parcial.movieservice.queue.MovieSender;
import com.parcial.movieservice.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * @author vaninagodoy
 */

@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {

    private final MovieSender sender;
    private final MovieService movieService;

    @Autowired
    public MovieController(MovieSender sender, MovieService movieService) {
        this.sender = sender;
        this.movieService = movieService;
    }

    @PostMapping("/save")
    ResponseEntity<Movie> saveMovie(@RequestBody Movie movie, @RequestParam (defaultValue = "true") Boolean throwError) {
        sender.send(movie);
        return ResponseEntity.ok().body(movieService.save(movie, throwError));
    }
}
