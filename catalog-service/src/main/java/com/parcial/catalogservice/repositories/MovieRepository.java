package com.parcial.catalogservice.repositories;

import com.parcial.catalogservice.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends MongoRepository<Movie,Long> {
    List<Movie> findAllByGenre(String genre);
}
