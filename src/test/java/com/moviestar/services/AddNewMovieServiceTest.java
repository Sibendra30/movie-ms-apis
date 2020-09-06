package com.moviestar.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.moviestar.controllers.TestUtil;
import com.moviestar.dao.MovieRepository;
import com.moviestar.models.Movie;

public class AddNewMovieServiceTest {
	
	private AddNewMovieService serviceUnderTest;
	private TestUtil util;
	
	@BeforeEach
	public void setup() {
		util = new TestUtil();
		serviceUnderTest = new AddNewMovieService();
		serviceUnderTest.movieRepository = Mockito.mock(MovieRepository.class);
	}
	
	@Test
	public void test_validate_nullBody() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {serviceUnderTest.validate();});
	}
	
	@Test
	public void test_validate_withId() {
		Movie movie = util.getMovieData();
		movie.setId(999l);
		serviceUnderTest.setReqBody(movie);
		Assertions.assertThrows(IllegalArgumentException.class, () -> {serviceUnderTest.validate();});
	}
	
	@Test
	public void test_validate_mandatoryFieldMissing() {
		Movie movie = util.getMovieData();
		movie.setName("");
		movie.setReleaseYear(0);
		serviceUnderTest.setReqBody(movie);
		Assertions.assertThrows(IllegalArgumentException.class, () -> {serviceUnderTest.validate();});
	}
	
	@Test
	public void test_validate_() {
		Movie movie = util.getMovieData();
		movie.setReleaseYear(0);
		serviceUnderTest.setReqBody(movie);
		Assertions.assertThrows(IllegalArgumentException.class, () -> {serviceUnderTest.validate();});
	}
	
	@Test
	public void test_executeImpl() {
		Movie movie = util.getMovieData();
		Movie movie1 = movie.clone();
		movie1.setId(999l);
		Mockito.doReturn(movie1).when(serviceUnderTest.movieRepository).save(Mockito.any(Movie.class));
		
		serviceUnderTest.setReqBody(movie);
		serviceUnderTest.executeImpl();
		Movie responseMovie= serviceUnderTest.getResponse();
		Assertions.assertNotNull(responseMovie);
		Assertions.assertEquals(responseMovie.getId(), movie1.getId());
	}

}
