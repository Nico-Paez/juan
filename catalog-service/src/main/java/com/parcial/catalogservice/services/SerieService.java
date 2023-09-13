package com.parcial.catalogservice.services;


import com.parcial.catalogservice.model.SerieRecord;
import com.parcial.catalogservice.models.Serie;
import com.parcial.catalogservice.repositories.SerieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SerieService {
    private final SerieRepository repository;

    public List<Serie> findByGenre (String genre){
        return repository.findAllByGenre(genre);
    }

    public Serie save (Serie serie){ return repository.save(serie);}
}
