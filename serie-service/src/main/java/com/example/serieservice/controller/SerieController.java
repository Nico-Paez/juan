package com.example.serieservice.controller;

import com.example.serieservice.model.Serie;
import com.example.serieservice.queue.SerieSender;
import com.example.serieservice.service.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author vaninagodoy
 */

@RestController
@RequestMapping("/api/v1/series")
public class SerieController {

    private final SerieSender sender;

    private final SerieService serieService;

    @Autowired
    public SerieController(SerieSender sender, SerieService serieService) {
        this.sender = sender;
        this.serieService = serieService;
    }

    @GetMapping
    public List<Serie> getAll() {
        return serieService.getAll();
    }

    @GetMapping("/{genre}")
    public List<Serie> getSerieByGenre(@PathVariable String genre) {
        return serieService.getSeriesBygGenre(genre);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@RequestBody Serie serie, @RequestParam (defaultValue = "true") Boolean throwError ){
        sender.send(serie);
        return serieService.create(serie,throwError);

    }
}
