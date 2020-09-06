package com.moviestar.dao;


import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.moviestar.models.Movie;

@Repository
@Transactional
public class MovieRepository {
	
	@PersistenceContext
	private EntityManager em;

	@Autowired
	private IMovieRepository repo;
	
	public Movie save(Movie movie) {
		return repo.save(movie);
	}
	
	public Optional<Movie> getMovieById(Long movieId) {
		return repo.findById(movieId);
	}
	
	public void deleteMovieById(Long movieId) {
		repo.deleteById(movieId);
	}
	
	public List<Movie> getAllMoviePagination(int offset, int limit) {
		Query query = em.createQuery("From Movie");
		query.setFirstResult(offset * limit); 
		query.setMaxResults(limit);
		return query.getResultList();
	}
	
	public long getTotalCount() {
		Query queryTotal = em.createQuery
			    ("Select count(m.id) from Movie m");
		return (long)queryTotal.getSingleResult();
	}
}
