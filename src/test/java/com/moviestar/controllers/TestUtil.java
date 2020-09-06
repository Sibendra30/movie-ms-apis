package com.moviestar.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.moviestar.models.GetAllMoviesResponse;
import com.moviestar.models.Movie;

public class TestUtil {
	Logger logger = LoggerFactory.getLogger(TestUtil.class);
	public GetAllMoviesResponse getMockedMovies() {
		GetAllMoviesResponse response = null;
		Gson gson = new Gson();
		byte[] jsonFileContent;
		try {
			jsonFileContent = Files.readAllBytes(Paths.get("./src/test/resources/GetAllMoviesResponse.json"));
			String json = new String(jsonFileContent);
			response = gson.fromJson(json, GetAllMoviesResponse.class);
		} catch (IOException e) {
			logger.error("Failed to read the json file", e);
		}
		return response;
	}
	
	public Movie getMovieData() {
		return new Movie(null, "Hulk", 2008, "Action", "Chris", 9.5, 160, null);
	}
}
