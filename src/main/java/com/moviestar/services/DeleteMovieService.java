package com.moviestar.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.moviestar.dao.MovieRepository;
import com.moviestar.error.ResourceNotFoundException;
import com.moviestar.models.Movie;

import javassist.NotFoundException;
import lombok.Setter;

@Service
@Scope(value="prototype")
public class DeleteMovieService extends BaseService {
	
	@Setter
	private Long movieId;
	
	@Autowired
	protected MovieRepository movieRepository;
	
	@Override
	protected void validate() {
		if (movieId == null) {
			throw new IllegalArgumentException("Movie Id cannot be null");
		}
	}

	@Override
	protected void executeImpl() throws ResourceNotFoundException {
		Optional<Movie> optionalMovie = movieRepository.getMovieById(movieId);
		if (optionalMovie.isPresent()) {
			movieRepository.deleteMovieById(movieId);
		} else {
			throw new ResourceNotFoundException("Movie with Id: " + movieId + " not found");
		}
	}

}
