package com.moviestar.controllers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.moviestar.models.GetAllMoviesResponse;
import com.moviestar.models.Movie;
import com.moviestar.services.AddNewMovieService;
import com.moviestar.services.DeleteMovieService;
import com.moviestar.services.GetAllMoviesService;
import com.moviestar.services.GetMovieByIdService;

import javassist.NotFoundException;

public class MovieControllerTest {

	private MovieController controller;
	private TestUtil util;
	
	@BeforeEach
	public void setUp() {
		controller = new MovieController();
		util = new TestUtil();
		controller.getAllMovieService = Mockito.mock(GetAllMoviesService.class);
		controller.getMovieByIdService = Mockito.mock(GetMovieByIdService.class);
		controller.addNewMovieService = Mockito.mock(AddNewMovieService.class);
		controller.deleteMovieService = Mockito.mock(DeleteMovieService.class);
	}
	
	@Test
	public void test_getAllMovies_success() throws Exception {
		
		Mockito.doNothing().when(controller.getAllMovieService).execute();
		Mockito.doReturn(util.getMockedMovies()).when(controller.getAllMovieService).getResponse();
		
		ResponseEntity<GetAllMoviesResponse> response = controller.getAllMovies(0, 3);
		
		Assertions.assertTrue(response.getStatusCode().ordinal() == HttpStatus.OK.ordinal());
		Assertions.assertNotNull(response.getBody());
		Assertions.assertTrue(response.getBody().getTotalCount() > 0);
	}
	
	@Test
	public void test_addNewMovie_success() throws Exception {
		Mockito.doNothing().when(controller.addNewMovieService).execute();
		Movie mockedData = util.getMovieData();
		mockedData.setId(999l);
		Mockito.doReturn(mockedData).when(controller.addNewMovieService).getResponse();
		
		ResponseEntity<Movie> response = controller.addNewMovie(util.getMovieData());
		Assertions.assertEquals(HttpStatus.CREATED.ordinal(), response.getStatusCode().ordinal());
		Assertions.assertNotNull(response.getBody());
		Assertions.assertNotNull(response.getBody().getId());
	}
	
	@Test
	public void test_getMovieById() throws Exception {
		Mockito.doNothing().when(controller.getMovieByIdService).execute();
		Movie mockedData = util.getMovieData();
		mockedData.setId(999l);
		Mockito.doReturn(mockedData).when(controller.getMovieByIdService).getResponse();
		
		ResponseEntity<Movie> response = controller.getMovieById(999l);
		Assertions.assertEquals(HttpStatus.OK.ordinal(), response.getStatusCode().ordinal());
		Assertions.assertNotNull(response.getBody());
	}
	
	@Test
	public void test_deleteMoviebyId() throws Exception {
		Mockito.doNothing().when(controller.deleteMovieService).execute();
		ResponseEntity<Void> response = controller.deleteMovie(999l);
		Assertions.assertEquals(HttpStatus.NO_CONTENT.ordinal(), response.getStatusCode().ordinal());
	}
	
}
