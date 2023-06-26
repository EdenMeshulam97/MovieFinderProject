package com.hit.dao;

import java.util.ArrayList;

import com.hit.dm.Movie;

public interface IDao {

	ArrayList<Movie> getMovies();
	boolean addMovie(Movie movie);
	boolean	deleteMovie(Movie movie);
}
