package com.moviestar.dao;

import org.springframework.data.repository.CrudRepository;

import com.moviestar.models.Movie;

public interface IMovieRepository extends CrudRepository<Movie, Long>{}
