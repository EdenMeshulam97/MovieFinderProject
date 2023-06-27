package com.hit.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import com.hit.algorithhm.IAlgoStringSearch;
import com.hit.algorithhm.KMPStringSearchAlgo;
import com.hit.algorithhm.RabinKrapStringSearchAlgo;
import com.hit.dao.Dao;
import com.hit.dao.IDao;
import com.hit.dm.Movie;
import com.hit.util.BackupAndRestore;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(OrderAnnotation.class)
public class MovieSearchServiceTest {

    private MovieSearchService movieSearchService;
    private IAlgoStringSearch searchAlgo;
    private IDao database;
    private Movie movie;
    private String sourceFilePath = "src/main/resources/DataBase.txt";
    private String testFilePath = "src/main/resources/TestDataBase.txt";
    private BackupAndRestore backupAndRestore;

    @BeforeEach
    void setUp() throws InterruptedException {
    	backupAndRestore = new BackupAndRestore();
    	backupAndRestore.backup(sourceFilePath, testFilePath,0,50000);
    	Thread.sleep(100);
    	backupAndRestore.stopBackup();
    	Thread.sleep(100);
        searchAlgo = new KMPStringSearchAlgo();
        database = new Dao(testFilePath);
        movie = new Movie("testMovieName", "TestMovieDesciption", "testTrailerLink");
        movieSearchService = new MovieSearchService(searchAlgo);
        movieSearchService.setDatabase(database);
    }

    @Test
    @Order(1)
    void addMovie() {
        assertTrue(movieSearchService.addMovie(movie));
    }

    @Test
    @Order(2)
    void searchMovie() {
        movieSearchService.addMovie(movie);
        assertEquals(1, movieSearchService.searchMovie("testMovieName").size());
    }
    
    @Test
    @Order(3)
    void removeMovie() {
        movieSearchService.addMovie(movie);
        assertTrue(movieSearchService.deleteMovie(movie));
    }

    @Test
    @Order(4)
    void searchMovieNoMatch() {
        movieSearchService.addMovie(movie);
        assertEquals(0, movieSearchService.searchMovie("not present").size());
    }

    @Test
    @Order(5)
    void getAndSetAlgo() {
        IAlgoStringSearch newAlgo = new RabinKrapStringSearchAlgo();
        movieSearchService.setAlgo(newAlgo);
        assertEquals(newAlgo, movieSearchService.getAlgo());
    }
    
    
}
