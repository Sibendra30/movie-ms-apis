package com.moviestar.dao;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.BeforeTestClass;

import com.moviestar.controllers.TestUtil;
import com.moviestar.models.Movie;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
public class MovieRepositoryTest {

	@Autowired
	private MovieRepository repoUnderTest;
	
	@Autowired
	private IMovieRepository genericRepo;
	
	private TestUtil util;
	
	@AfterEach
	public void cleanDB() {
		genericRepo.deleteAll();
	}
	
	@BeforeAll
	public void setup() {
		util = new TestUtil();
	}

	@Test
	public void test_save() {
		Movie movie = util.getMovieData();
		Movie newMovie = repoUnderTest.save(movie);
		if (newMovie != null) {
			Assertions.assertNotNull(newMovie.getId());
			Assertions.assertEquals(movie.getName(), newMovie.getName());;
		} else {
			Assertions.fail("Entity not saved");
		}
	}

	@Test
	public void test_findMovieById() {
		Movie movie = util.getMovieData();
		Movie newMovie = genericRepo.save(movie);
		Optional<Movie> optMovie = repoUnderTest.getMovieById(newMovie.getId());

		if(optMovie.isPresent()) {
			Assertions.assertTrue(newMovie.equals(optMovie.get()));
		} else {
			Assertions.fail("Entity not available");
		}

	}
	
	@Test
	public void test_deleteMovieById() {
		Movie movie = util.getMovieData();
		Movie newMovie = genericRepo.save(movie);
		repoUnderTest.deleteMovieById(newMovie.getId());
		Optional<Movie> optionalMovie = genericRepo.findById(newMovie.getId());
		if(optionalMovie.isPresent()) {
			Assertions.fail("Delete operation failed");
		}
	}
	
	@Test
	public void test_getAllMoviesByPagination() {
		this.insertBulkRecord();
		
		List<Movie> list = repoUnderTest.getAllMoviePagination(0, 3);
		Assertions.assertTrue(list.size() == 3);
		
		list = repoUnderTest.getAllMoviePagination(1, 3);
		Assertions.assertTrue(list.size() == 1);
	}

	@Test
	public void test_totalCount() {
		long recordInserted = this.insertBulkRecord().size();
		Assertions.assertEquals(repoUnderTest.getTotalCount(), recordInserted);
	}
	
	private List<Movie> insertBulkRecord() {
		List<Movie> movies = new ArrayList<>();
		Movie movie = new Movie(null, "Hulk", 2008, "Action", "Chris", 9.5, 160, LocalTime.now());
		movies.add(genericRepo.save(movie));
		movie = new Movie(null, "Mirzapur", 2018, "Drama", "Sunny", 9.9, 260, LocalTime.now());
		movies.add(genericRepo.save(movie));
		movie = new Movie(null, "ACID", 2012, "Hrror", "RVK", 7.9, 110, LocalTime.now());
		movies.add(genericRepo.save(movie));
		movie = new Movie(null, "Horror-Drama", 2010, "Comedy", "Jeeva", 6.9, 170, LocalTime.now());
		movies.add(genericRepo.save(movie));
		
		return movies;
	}
	
}