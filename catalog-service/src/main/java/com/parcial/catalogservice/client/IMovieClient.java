package com.parcial.catalogservice.client;

import com.parcial.catalogservice.model.MovieRecord;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "movie-service")
@LoadBalancerClient(name = "movie-service", configuration = FeignConfiguration.class)
public interface IMovieClient {

    @GetMapping("/api/v1/movies/{genre}")
    ResponseEntity<List<MovieRecord>> getMovieByGenre(@PathVariable String genre);

    @PostMapping("/api/v1/movies/save")
    ResponseEntity<MovieRecord> saveMovie(@RequestBody MovieRecord movie, @RequestParam Boolean throwError);
}
