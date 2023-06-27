package com.hit.service;

import java.util.ArrayList;

import com.hit.algorithhm.IAlgoStringSearch;
import com.hit.dao.Dao;
import com.hit.dao.IDao;
import com.hit.dm.Movie;

public class MovieSearchService {

    private IAlgoStringSearch searchAlgo;
    private IDao database;
    
    // Constructor initializing the search algorithm and database
    public MovieSearchService(IAlgoStringSearch searchAlgo){
    	if (searchAlgo == null) {
    	    throw new IllegalArgumentException("Search algorithm cannot be null");
    	}
    	this.searchAlgo = searchAlgo;
    	this.database = new Dao("src/main/resources/DataBase.txt");
    }
    
    // Method to add a new movie to the database
    public boolean addMovie(Movie movie) {
        if (movie == null || !movie.validate()) {
        	return false;
        }
        return database.addMovie(movie);
    }
    
    // Method to delete a movie from the database
    public boolean deleteMovie(Movie movie) {
    	if (movie == null) {
    		return false;
        }
        return database.deleteMovie(movie);
    }
    
    // Method to search for a movie by a key
    public ArrayList<Movie> searchMovie(String key) {
    	ArrayList<Movie> result = new ArrayList<Movie>();

    	if (key == null || key.isEmpty()) {
    	    return result;
    	}

        ArrayList<Movie> movies = database.getMovies();
        for (Movie movie : movies) {
            // Search in the movie's name and description
            if (!searchAlgo.search(movie.getName(), key).isEmpty() 
                || !searchAlgo.search(movie.getDescription(), key).isEmpty()) {
            	result.add(movie);
            }
        }
    	return result;
    }
    
    // Getter for the search algorithm
    public IAlgoStringSearch getAlgo() {
        return searchAlgo;
    }

    // Setter for the search algorithm
    public void setAlgo(IAlgoStringSearch searchAlgo) {
        if (searchAlgo == null) {
    	    throw new IllegalArgumentException("Search algorithm cannot be null");
    	}
        this.searchAlgo = searchAlgo;
    }
 // Setter for the search algorithm
    public void setDatabase(IDao dao) {
        if (dao == null) {
    	    throw new IllegalArgumentException("Database cannot be null");
    	}
        this.database = dao;
    }
}
