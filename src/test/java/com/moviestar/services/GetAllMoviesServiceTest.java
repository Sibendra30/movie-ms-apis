package com.moviestar.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.moviestar.controllers.TestUtil;
import com.moviestar.dao.MovieRepository;
import com.moviestar.models.GetAllMoviesResponse;

public class GetAllMoviesServiceTest {

	private GetAllMoviesService service;
	private TestUtil util;
	
	@BeforeEach
	public void setup() {
		service = new GetAllMoviesService();
		service.repository = Mockito.mock(MovieRepository.class);
		util = new TestUtil();
	}
	
	@Test
	public void test_executeImpl_defaultOffsetLimit() {
		
		GetAllMoviesResponse mockedResponse = util.getMockedMovies();
		Mockito.doReturn(mockedResponse.getMovies())
			.when(service.repository)
			.getAllMoviePagination(Mockito.anyInt(), Mockito.anyInt());
		
		Mockito.doReturn(mockedResponse.getTotalCount())
		.when(service.repository)
		.getTotalCount();
		
		service.setOffset(0);
		service.setLimit(10);
		
		service.executeImpl();
		GetAllMoviesResponse response = service.getResponse();
		Assertions.assertNotNull(response);
		Assertions.assertEquals(response.getTotalCount(), mockedResponse.getTotalCount());
		Assertions.assertEquals(response.getNextOffset(), null);
		Assertions.assertFalse(response.isMoreRecords());
	}
	
	@Test
	public void test_executeImpl_withOffsetLimit() {
		GetAllMoviesResponse mockedResponse = util.getMockedMovies();
		Mockito.doReturn(mockedResponse.getMovies())
			.when(service.repository)
			.getAllMoviePagination(Mockito.anyInt(), Mockito.anyInt());
		
		Mockito.doReturn(mockedResponse.getTotalCount())
		.when(service.repository)
		.getTotalCount();
		
		service.setOffset(0);
		service.setLimit(3);
		service.executeImpl();
		GetAllMoviesResponse response = service.getResponse();
		
		Assertions.assertNotNull(response);
		Assertions.assertEquals(response.getTotalCount(), mockedResponse.getTotalCount());
		Assertions.assertEquals(response.getNextOffset(), 1);
		Assertions.assertTrue(response.isMoreRecords());
	}
}
