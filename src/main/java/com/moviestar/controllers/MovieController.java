package com.moviestar.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.moviestar.models.GetAllMoviesResponse;
import com.moviestar.models.Movie;
import com.moviestar.services.AddNewMovieService;
import com.moviestar.services.DeleteMovieService;
import com.moviestar.services.GetAllMoviesService;
import com.moviestar.services.GetMovieByIdService;

@RestController
@Scope("prototype")
public class MovieController implements IMovieController{
	
	@Autowired
	protected GetAllMoviesService getAllMovieService;
	@Autowired
	protected GetMovieByIdService getMovieByIdService;
	@Autowired
	protected DeleteMovieService deleteMovieService;
	@Autowired
	protected AddNewMovieService addNewMovieService;
	
	public ResponseEntity<GetAllMoviesResponse> getAllMovies(
			@RequestParam(value="offset", defaultValue="0", required=false) Integer offset, 
			@RequestParam(value="limit", defaultValue="10", required=false) Integer limit) throws Exception {
		getAllMovieService.setOffset(offset);
		getAllMovieService.setLimit(limit);
		getAllMovieService.execute();
		return new ResponseEntity<GetAllMoviesResponse>(getAllMovieService.getResponse(), HttpStatus.OK);
	}
	
	public ResponseEntity<Movie> getMovieById(@PathVariable("movieId") Long movieId) throws Exception {
		getMovieByIdService.setMovieId(movieId);
		getMovieByIdService.execute();
		return new ResponseEntity<Movie>(getMovieByIdService.getResponse(), HttpStatus.OK);
	}
	
	public ResponseEntity<Movie> addNewMovie(@RequestBody Movie reqBody) throws Exception {
		addNewMovieService.setReqBody(reqBody);
		addNewMovieService.execute();
		Movie newMovie = addNewMovieService.getResponse();
		return new ResponseEntity<Movie>(newMovie, HttpStatus.CREATED);
	}
	
	public ResponseEntity<Void> deleteMovie(@PathVariable("movieId") Long movieId) throws Exception {
		deleteMovieService.setMovieId(movieId);
		deleteMovieService.execute();
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

}
