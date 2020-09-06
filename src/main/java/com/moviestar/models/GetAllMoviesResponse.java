package com.moviestar.models;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class GetAllMoviesResponse implements Serializable{

	private static final long serialVersionUID = -2625323691720066474L;
	private Long totalCount;
	private Integer nextOffset;
	private boolean moreRecords;
	private List<Movie> movies;
}
