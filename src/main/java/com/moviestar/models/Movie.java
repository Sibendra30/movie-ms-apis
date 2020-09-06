package com.moviestar.models;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.Gson;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor @EqualsAndHashCode @AllArgsConstructor
public class Movie implements Serializable{

	private static final long serialVersionUID = 4940285737612569584L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private Integer releaseYear;
	private String genre;
	private String director;
	private Double rating;
	private Integer length;
	
	@JsonIgnore
	private LocalTime creationDate;
	
	public Movie clone() {
		Gson gson = new Gson();
		return gson.fromJson(gson.toJson(this), Movie.class);
	}
	
}
