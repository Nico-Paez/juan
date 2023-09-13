package com.parcial.catalogservice.queue;

import com.parcial.catalogservice.model.Movie;
import com.parcial.catalogservice.model.Serie;
import com.parcial.catalogservice.repositories.MovieRepository;
import com.parcial.catalogservice.repositories.SerieRepository;
import com.parcial.catalogservice.services.MovieService;
import com.parcial.catalogservice.services.SerieService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;


@Component
public class CatalogListener {

    private final MovieService movieService;
    private final SerieService serieService;

    public CatalogListener(MovieService movieService, SerieService serieService) {
        this.movieService = movieService;
        this.serieService = serieService;
    }

    @RabbitListener(queues = {"${queue.movie.name}"})
    public void receiveMovie(@Payload Movie movie){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        movieService.save(movie);
    }

    @RabbitListener(queues = {"${queue.serie.name}"})
    public void receiveMovie(@Payload Serie serie){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        serieService.save(serie);
    }
}
