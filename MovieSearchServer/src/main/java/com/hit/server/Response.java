package com.hit.server;



import java.util.ArrayList;

import com.hit.dm.Movie;

public class Response {

    private boolean success = true;
    private ArrayList<Movie> moviesList;
    private String error;
    
    
    public boolean isSuccess() {
        return this.success;
    }
    
    public void setSuccess(Boolean success) {
        if (success == null) {
            throw new IllegalArgumentException("Success can't be null.");
        }
        this.success = success;
    }
    
    public String getError() {
        return error;
    }

    public void setError(String error) {
        if (error == null) {
            throw new IllegalArgumentException("Error message can't be null. Use an empty string instead.");
        }
        this.error = error;
    }
    
    public ArrayList<Movie> getMoviesList() {
        return moviesList;
    }
    
    public void setMoviesList(ArrayList<Movie> movies) {
        if (movies == null) {
            throw new IllegalArgumentException("Movies list can't be null. Use an empty list instead.");
        }
        this.moviesList = movies;
    }
}
