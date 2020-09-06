package com.moviestar.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.moviestar.dao.MovieRepository;
import com.moviestar.models.GetAllMoviesResponse;

import lombok.Setter;

@Service
@Scope(value="prototype")
public class GetAllMoviesService extends BaseService{
	
	private GetAllMoviesResponse response;
	
	@Setter
	private Integer offset;
	@Setter
	private Integer limit;
	
	@Autowired
	protected MovieRepository repository;
	
	protected void validate() {
		if (offset == null || offset < 0) {
			throw new IllegalArgumentException("Invalid value of offset");
		}
	}

	@Override
	protected void executeImpl() {
		response = new GetAllMoviesResponse();
		long totalRowCount = repository.getTotalCount();
		if ((offset + 1) * limit >= totalRowCount) {
			response.setMoreRecords(false);
		} else {
			response.setMoreRecords(true);
			response.setNextOffset(offset + 1);
		}
		response.setTotalCount(totalRowCount);
		response.setMovies(repository.getAllMoviePagination(offset, limit));
	}

	public GetAllMoviesResponse getResponse() {
		return response;
	}
}
