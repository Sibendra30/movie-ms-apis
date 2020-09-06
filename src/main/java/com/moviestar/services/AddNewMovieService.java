package com.moviestar.services;

import java.time.LocalTime;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.ServletRequestBindingException;

import com.moviestar.dao.MovieRepository;
import com.moviestar.models.Movie;

import lombok.Setter;

@Service
@Scope(value="prototype")
public class AddNewMovieService extends BaseService {
	
	@Setter
	private Movie reqBody;
	
	private Movie insertedMovie;
	
	@Autowired
	protected MovieRepository movieRepository;
	
	@Override
	protected void validate() {
		super.validate();
		if (reqBody == null) {
			throw new IllegalArgumentException("request body cannot be null");
		}
		
		if (reqBody.getId() != null) {
			throw new IllegalArgumentException("Id cannot be pre populated");
		}
		
		if (StringUtils.isEmpty(reqBody.getName()) 
				|| StringUtils.isEmpty(reqBody.getGenre())
				|| StringUtils.isEmpty(reqBody.getDirector())
				|| reqBody.getReleaseYear() == null
				|| reqBody.getReleaseYear() < 1900
				|| reqBody.getReleaseYear() > 2050) {
			throw new IllegalArgumentException("Missing mandatory parameters or invalid values");
		}
	}

	@Override
	protected void executeImpl() {
		reqBody.setCreationDate(LocalTime.now());
		insertedMovie = movieRepository.save(this.reqBody);
	}
	
	public Movie getResponse() {
		return insertedMovie;
	}

}
