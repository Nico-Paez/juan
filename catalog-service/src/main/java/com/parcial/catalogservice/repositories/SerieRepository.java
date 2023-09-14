package com.parcial.catalogservice.repositories;


import com.parcial.catalogservice.models.Serie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
public interface SerieRepository extends MongoRepository<Serie,Long> {
    List<Serie> findAllByGenre(String genre);
    List<Serie> findAll();
}
