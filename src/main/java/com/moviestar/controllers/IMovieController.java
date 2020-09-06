package com.moviestar.controllers;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.moviestar.models.GetAllMoviesResponse;
import com.moviestar.models.Movie;

@RequestMapping(value="/movies")
public interface IMovieController {

	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<GetAllMoviesResponse> getAllMovies(
			@RequestParam("offset") Integer offset,
			@RequestParam("limit") Integer limit) throws Exception;
	
	@RequestMapping(value="/{movieId}",method=RequestMethod.GET)
	public ResponseEntity<Movie> getMovieById(@PathVariable("movieId") Long movieId) throws Exception;
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public ResponseEntity<Movie> addNewMovie(@RequestBody Movie reqBody) throws Exception;
	
	@RequestMapping(value="/{movieId}",method=RequestMethod.DELETE)
	public ResponseEntity<Void> deleteMovie(@PathVariable("movieId") Long movieId) throws Exception;
}
