package com.hit.controller;

import com.hit.dm.Movie;
import com.hit.server.Request;
import com.hit.server.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MovieControllerTest {

    private MovieController movieController;

    @BeforeEach
    void setUp() {
        movieController = new MovieController();
    }

    @AfterEach
    void tearDown() {
        movieController = null;
    }

    @Test
    void testPerformActionAddMovie() {
        // Prepare a Request object with an "add" action and a Movie object
        Request request = new Request(Map.of("action", "add"), new Movie("Test Movie", "This is a test movie", "http://test.movie"));

        // Perform the action
        Response response = movieController.performAction(request);

        // Check the response for success
        assertTrue(response.isSuccess());
    }

    @Test
    void testPerformActionDeleteMovie() {
        // Prepare a Request object with a "delete" action and a Movie object
        Request request = new Request(Map.of("action", "delete"), Map.of("movie", "{\"name\":\"movie test\",\"description\":\"test Descroption\",\"trailerLink\":\"http://test.movie\"}"));
        // Perform the action
        Response response = movieController.performAction(request);

        // Check the response for success
        assertTrue(response.isSuccess());
    }

    @Test
    void testPerformActionSearchMovie() {
        // Prepare a Request object with a "search" action
        Request request = new Request(Map.of("action", "search"), Map.of("algorithmType", "kmp", "searchKeywords", "test"));

        // Perform the action
        Response response = movieController.performAction(request);

        // Check the response for success and results
        assertTrue(response.isSuccess());
        assertFalse(response.getMoviesList().isEmpty());
    }

    @Test
    void testPerformActionInvalidAction() {
        // Prepare a Request object with an invalid action
        Request request = new Request(Map.of("action", "invalid"), null);

        // Perform the action
        Response response = movieController.performAction(request);

        // Check the response for failure and an error message
        assertFalse(response.isSuccess());
        assertEquals("Given action does not exist.", response.getError());
    }

    @Test
    void testPerformActionMissingAction() {
        // Prepare a Request object without an action
        Request request = new Request(Map.of(), null);

        // Perform the action
        Response response = movieController.performAction(request);

        // Check the response for failure and an error message
        assertFalse(response.isSuccess());
        assertEquals("Could not find action header key.", response.getError());
    }

    @Test
    void testPerformActionSearchMovieMissingAlgorithmType() {
        // Prepare a Request object with a "search" action but missing the algorithmType
        Request request = new Request(Map.of("action", "search"), Map.of("searchKeywords", "test"));

        // Perform the action
        Response response = movieController.performAction(request);

        // Check the response for failure and an error message
        assertFalse(response.isSuccess());
        assertEquals("Search action must have algorithmType and searchKeywords in order to operate.", response.getError());
    }

    
}
