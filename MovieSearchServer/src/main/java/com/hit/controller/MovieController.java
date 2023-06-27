package com.hit.controller;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.hit.algorithhm.KMPStringSearchAlgo;
import com.hit.algorithhm.NaiveStringSearchAlgo;
import com.hit.algorithhm.RabinKrapStringSearchAlgo;
import com.hit.dm.Movie;
import com.hit.server.Request;
import com.hit.server.Response;
import com.hit.service.MovieSearchService;



public class MovieController {
    MovieSearchService movieSearchService = new MovieSearchService(new KMPStringSearchAlgo());

    // Main method to handle requests and perform actions
    public Response performAction(Request request) {

        Response response = new Response();

        // Check if the action key exists in the header
        if(request.getHeaders().containsKey("action")) {
            String action = request.getHeaders().get("action").toString();
            switch (action)
            {
                case "add":
                    response = handleAddAction(request);
                    break;
                case "delete":
                    response = handleDeleteAction(request);
                    break;
                case "search":
                    response = handleSearchAction(request);
                    break;
                default :
                    response = handleInvalidAction();
            }
        }
        else {
            response.setSuccess(false);
            response.setError("Could not find action header key.");
        }
        return response;
    }
    
    private Response handleInvalidAction() {
        Response response = new Response();
        response.setSuccess(false);
        response.setError("Given action does not exist.");
        return response;
    }
    
    private Response handleAddAction(Request request) {
        Response response = new Response();
        if(validateBodyForAddDelete(request)) {
            boolean add = movieSearchService.addMovie(getMovieFromRequestBody(request));
            if (!add) {
                response.setError("Error, Failed to Add item");
                response.setSuccess(false);
            }
        } else {
            response.setSuccess(false);
            response.setError("Add action must have movie and all required fields in order to operate.");
        }
        return response;
    }

    private Response handleDeleteAction(Request request) {
        Response response = new Response();
        if(validateBodyForAddDelete(request)) {
            boolean remove = movieSearchService.deleteMovie(getMovieFromRequestBody(request));
            if(!remove) {
                response.setError("Failed to remove item");
                response.setSuccess(false);
            }
        } else {
            response.setSuccess(false);
            response.setError("Delete action must have movie and all required fields in order to operate.");
        }
        return response;
    }
    
    private Response handleSearchAction(Request request) {
        Response response = new Response();
        if(validateBodyForSearchMovie(request)) {
            response.setMoviesList(searchMovie(request));
        } else {
            response.setSuccess(false);
            response.setError("Search action must have algorithmType and searchKeywords in order to operate.");
        }
        return response;
    }
    
    public ArrayList<Movie> searchMovie(Request request) {

        ArrayList<Movie> resultList = new ArrayList<Movie>();
        if(validateBodyForSearchMovie(request)) {
        	switch(getAlgorithmTypeFromRequestBody(request)){
            case "kmp":
                System.out.println("KMP");
                movieSearchService.setAlgo(new KMPStringSearchAlgo());
                break;
            case "naive":
                System.out.println("Naive");
                movieSearchService.setAlgo(new NaiveStringSearchAlgo());
                break;
            case "rabinkrap":
            	movieSearchService.setAlgo(new RabinKrapStringSearchAlgo());
                break;
        	}
        	resultList = movieSearchService.searchMovie(getSearchKeywardsFromRequestBody(request));
        }
        

        return resultList;
    }
    
    private JsonObject getRequestJsonBody(Request request) {
    	Gson Gson = new Gson();
    	JsonElement jsonElement = Gson.toJsonTree(request.getBody());
    	JsonObject json = jsonElement.getAsJsonObject();
    	return json;
    }
    
    private boolean validateBodyForSearchMovie(Request request) {
    	JsonObject json = getRequestJsonBody(request);
    	boolean result = json.has("algorithmType") && json.has("searchKeywords");
    	return result;
    }
    
    private String getAlgorithmTypeFromRequestBody(Request request) {
    	String result = null;
    	JsonObject json = getRequestJsonBody(request);
    	result = json.get("algorithmType").toString().toLowerCase();
    	return result;
    }
    
    private String getSearchKeywardsFromRequestBody(Request request){
    	String result = null;
    	JsonObject json = getRequestJsonBody(request);
    	result = json.get("searchKeywords").toString();
    	return result;
    }
    
    private boolean validateBodyForAddDelete(Request request){
    	boolean result = false;
    	JsonObject json = getRequestJsonBody(request);
    	result = json.has("movie");
    	if(result)
    	{
    		JsonObject movieJson = json.get("movie").getAsJsonObject();
    		result = json.has("name") && json.has("description") && json.has("trailerLink");
    	}
    	return result;
    }
    
    private Movie getMovieFromRequestBody(Request request) {
    	String name = null;
    	String description = null;
    	String trailerLink = null;
    	
    	JsonObject json = getRequestJsonBody(request);
    	JsonObject movieJson = json.get("movie").getAsJsonObject();
    	
    	name = movieJson.get("name").toString();
    	description = movieJson.get("description").toString();
    	trailerLink = movieJson.get("trailerLink").toString();
    	return new Movie(name, description, trailerLink);
    }
}
