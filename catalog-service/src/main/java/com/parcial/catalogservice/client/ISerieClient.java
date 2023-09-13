package com.parcial.catalogservice.client;

import com.parcial.catalogservice.model.SerieRecord;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "serie-service")
@LoadBalancerClient(name = "serie-service", configuration = FeignConfiguration.class)
public interface ISerieClient {

    @GetMapping("/api/v1/series")
    ResponseEntity<List<SerieRecord>> getAll();

    @GetMapping("/api/v1/series/{genre}")
    ResponseEntity<List<SerieRecord>> getSerieByGenre(@PathVariable String genre);

    @PostMapping("/api/v1/series")
    @ResponseStatus(HttpStatus.CREATED)
    String create(@RequestBody SerieRecord serie);
}
