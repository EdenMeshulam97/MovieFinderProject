package com.hit.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hit.dm.Movie;
import java.io.FileWriter;
import java.io.Writer;

public class Dao implements IDao {
	
	private String dbPath;
	
	public Dao(String dbPath) {

        this.dbPath = dbPath;
    }
	
	
	
	@Override
	public ArrayList<Movie> getMovies() 
	{
		ArrayList<Movie> movies = new ArrayList<Movie>();

	    if (dbPath != null) {
	        try (Reader reader = new FileReader(dbPath)) {
	            Gson gson = new Gson();
	            Type listType = new TypeToken<ArrayList<Movie>>() {}.getType();
	            movies = gson.fromJson(reader, listType);
	        } catch (FileNotFoundException e) {
	            System.out.println("DB File not found.");
	            e.printStackTrace();
	        } catch (IOException e) {
	            System.out.println("Unable to read DB File.");
	            e.printStackTrace();
	        }
	    } else {
	        System.out.println("DB Path is null.");
	    }

	    return movies;
	}
	
	@Override
	public boolean addMovie(Movie movie)
	{
		boolean result = false;
	    ArrayList<Movie> movies = getMovies();

	    // Check if the movie is already in the list
	    if (!movies.contains(movie)) {
	        movies.add(movie);

	        try (Writer writer = new FileWriter(dbPath)) {
	            Gson gson = new Gson();
	            gson.toJson(movies, writer);
	            result = true;
	        } catch (IOException e) {
	            System.out.println("Unable to write to DB File.");
	            e.printStackTrace();
	        }
	    }

	    return result;
	}
	
	@Override
	public boolean deleteMovie(Movie movie)
	{
		boolean result = false;
	    ArrayList<Movie> movies = getMovies();
	    boolean isMoviePresent = movies.remove(movie);

	    if (isMoviePresent) {
	        try (Writer writer = new FileWriter(dbPath)) {
	            Gson gson = new Gson();
	            gson.toJson(movies, writer);
	            result = true;
	        } catch (IOException e) {
	            System.out.println("Unable to write to DB File.");
	            e.printStackTrace();
	        }
	    }

	    return result;
	}
}
