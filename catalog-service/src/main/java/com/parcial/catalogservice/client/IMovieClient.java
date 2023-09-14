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

    @PostMapping("/api/v1/movies/save")
    String saveMovie(@RequestBody MovieRecord movie, @RequestParam Boolean throwError);
}
