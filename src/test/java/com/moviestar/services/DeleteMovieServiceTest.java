package com.moviestar.services;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.moviestar.controllers.TestUtil;
import com.moviestar.dao.MovieRepository;
import com.moviestar.error.ResourceNotFoundException;
import com.moviestar.models.Movie;

import javassist.NotFoundException;

public class DeleteMovieServiceTest {
	
	private DeleteMovieService serviceUnderTest;
	private TestUtil util;
	
	@BeforeEach
	public void setup() {
		serviceUnderTest = new DeleteMovieService();
		util = new TestUtil();
		serviceUnderTest.movieRepository = Mockito.mock(MovieRepository.class);
	}
	
	@Test
	public void test_validate_failure() {
		serviceUnderTest.setMovieId(null);
		Assertions.assertThrows(IllegalArgumentException.class, 
				() -> {serviceUnderTest.validate();});
	}
	
	@Test
	public void test_executeImpl_success() throws ResourceNotFoundException {
		Movie mockedData = util.getMovieData();
		mockedData.setId(999l);
		Optional<Movie> optionalData = Optional.of(mockedData);
		Mockito
			.doReturn(optionalData)
			.when(serviceUnderTest.movieRepository)
			.getMovieById(Mockito.anyLong());
			
		serviceUnderTest.setMovieId(999l);
		serviceUnderTest.executeImpl();
	}
	
	@Test
	public void test_executeImpl_failure() {
		Optional<Movie> optionalData = Optional.empty();
		Mockito
			.doReturn(optionalData)
			.when(serviceUnderTest.movieRepository)
			.getMovieById(Mockito.anyLong());
			
		serviceUnderTest.setMovieId(999l);
		Assertions.assertThrows(ResourceNotFoundException.class,
				() -> {serviceUnderTest.executeImpl();});
	}

}
