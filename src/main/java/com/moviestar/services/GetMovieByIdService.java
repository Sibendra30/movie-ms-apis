package com.moviestar.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.moviestar.dao.MovieRepository;
import com.moviestar.error.ResourceNotFoundException;
import com.moviestar.models.Movie;

import lombok.Setter;

@Service
@Scope(value="prototype")
public class GetMovieByIdService extends BaseService{
	
	@Setter
	private Long movieId;
	
	private Movie response;
	
	@Autowired
	protected MovieRepository movieRepository;
	
	protected void validate() {
		if (movieId == null) {
			throw new IllegalArgumentException("movieId cannot be null/empty");
		}
	}

	@Override
	protected void executeImpl() throws ResourceNotFoundException {
		Optional<Movie> optionalMovie = movieRepository.getMovieById(movieId);
		if (optionalMovie.isPresent()) {
			response = optionalMovie.get();
		} else {
			throw new ResourceNotFoundException("Movie with Id: " + movieId + " not found");
		}
	}
	
	public Movie getResponse() {
		return response;
	}

}
