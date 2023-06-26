package com.hit.service;

import java.util.ArrayList;

import com.hit.algorithhm.IAlgoStringSearch;
import com.hit.dao.*;
import com.hit.dm.Movie;

public class MovieSearchService {

    IAlgoStringSearch searchAlgo;
    IDao database;
    
    public MovieSearchService(IAlgoStringSearch searchAlgo){
    	this.searchAlgo = searchAlgo;
    	this.database = new Dao("resources/DataBase.txt");
    }
    
    public boolean addItem(Movie item) {
    	boolean result = false;
        if (item != null && item.validate()) {
        	result = database.addMovie(item);
        }
        return result;
    }
    
    public boolean removeItem(Movie item) {
        boolean result = false;
    	if (item != null) {
    		result = database.deleteMovie(item);;
        }
        return result;
    }
    
    public ArrayList<Movie> searchItem(String key)
    {
    	 ArrayList<Movie> result = new ArrayList<Movie>();

    	    if (key != null && !key.isEmpty()) {
    	        ArrayList<Movie> arr = database.getMovies();

    	        for (Movie item : arr) {
    	            if (!searchAlgo.search(item.getName(), key).isEmpty() 
    	                || !searchAlgo.search(item.getDescription(), key).isEmpty()) {
    	            	result.add(item);
    	            }
    	        }
    	    }

    	    return result;
    }
    
    public IAlgoStringSearch getAlgo() {
        return searchAlgo;
    }

    public void setAlgo(IAlgoStringSearch searchAlgo) {
        this.searchAlgo = searchAlgo;
    }
}
