package com.parcial.catalogservice.controller;

import com.parcial.catalogservice.client.IMovieClient;
import com.parcial.catalogservice.client.ISerieClient;
import com.parcial.catalogservice.model.MovieRecord;
import com.parcial.catalogservice.model.SerieRecord;
import com.parcial.catalogservice.models.Movie;
import com.parcial.catalogservice.models.Serie;
import com.parcial.catalogservice.services.MovieService;
import com.parcial.catalogservice.services.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class CatalogController {


    private IMovieClient iMovieClient;

    private MovieService movieService;
    private SerieService serieService;

    private ISerieClient iSerieClient;

    @Autowired
    public CatalogController(IMovieClient iMovieClient, MovieService movieService, SerieService serieService, ISerieClient iSerieClient) {
        this.iMovieClient = iMovieClient;
        this.movieService = movieService;
        this.serieService = serieService;
        this.iSerieClient = iSerieClient;
    }




    @GetMapping("/catalog/{genre}")
    public ResponseEntity<List<Movie>> getMovieByGenre (@PathVariable String genre){
        return ResponseEntity.ok().body(movieService.findByGenre(genre));
    }

    @PostMapping("/catalog/save")
    ResponseEntity<MovieRecord> saveMovie(@RequestBody MovieRecord movie) {
        return iMovieClient.saveMovie(movie);
    }

    @GetMapping("/catalog/serie/{genre}")
    public ResponseEntity<List<Serie>> getSeriesByGenre (@PathVariable String genre){
        return ResponseEntity.ok().body(serieService.findByGenre(genre));
    }

    @PostMapping("/catalog/serie/save")
    public String saveSerie(@RequestBody SerieRecord serie) {
        return iSerieClient.create(serie);
    }
}
