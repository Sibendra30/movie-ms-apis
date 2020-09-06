package com.moviestar.services;

import java.time.LocalTime;
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

public class GetMovieByIdServiceTest {

	private GetMovieByIdService serviceUnderTest;
	private TestUtil util;
	
	@BeforeEach
	public void setup() {
		serviceUnderTest = new GetMovieByIdService();
		util = new TestUtil();
		serviceUnderTest.movieRepository = Mockito.mock(MovieRepository.class);
	}
	
	@Test
	public void test_validate_nullId() {
		Assertions.assertThrows(IllegalArgumentException.class, 
				() -> {serviceUnderTest.validate();});
	}
	
	@Test
	public void test_executeImpl_notFound() {
		serviceUnderTest.setMovieId(999l);
		Assertions.assertThrows(ResourceNotFoundException.class, ()-> {serviceUnderTest.executeImpl();});
	}
	
	@Test
	public void test_executeImpl_success() throws ResourceNotFoundException {
		Movie mockedData = util.getMovieData();
		mockedData.setId(999l);
		mockedData.setCreationDate(LocalTime.now());
		Optional<Movie> optionalMovieData= Optional.of(mockedData);
		Mockito
			.doReturn(optionalMovieData)
			.when(serviceUnderTest.movieRepository)
			.getMovieById(Mockito.anyLong());
		
		serviceUnderTest.setMovieId(999l);
		serviceUnderTest.executeImpl();
		Movie retrievedData = serviceUnderTest.getResponse();
		
		Assertions.assertNotNull(retrievedData);
		Assertions.assertNotNull(retrievedData.getId());
		Assertions.assertNotNull(retrievedData.getName());
		Assertions.assertNotNull(retrievedData.getReleaseYear());
		Assertions.assertNotNull(retrievedData.getGenre());
	}
}
