package com.hit.dao;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import com.hit.dm.Movie;
import com.hit.util.BackupAndRestore;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

public class DaoTest {
    
    private Dao dao;
    private Movie testMovie1;
    private Movie testMovie2;
    private String sourceFilePath = "src/main/resources/DataBase.txt";
    private String testFilePath = "src/main/resources/TestDataBase.txt";
    private BackupAndRestore backupAndRestore;

    @Before
    public void setUp() throws IOException, InterruptedException {
        
        //Files.copy(Paths.get(dbPath), Paths.get(testDBPath));
    	backupAndRestore = new BackupAndRestore();
    	backupAndRestore.backup(sourceFilePath, testFilePath,0,50000);
    	Thread.sleep(100);
    	backupAndRestore.stopBackup();
    	Thread.sleep(100);
        dao = new Dao(testFilePath);
        testMovie1 = new Movie("Test1", "Test Description1", "Test Link1");
        testMovie2 = new Movie("Test2", "Test Description2", "Test Link2");
    }

    

	  @Test public void testAddMovie() { 
		  assertTrue(dao.addMovie(testMovie1));
		  assertTrue(dao.getMovies().contains(testMovie1)); 
		  }
	  
	  @Test public void testAddExistingMovie() {
		  dao.addMovie(testMovie1);
		  assertFalse(dao.addMovie(testMovie1)); 
	  }
	 

    @Test
    public void testGetMovies() {
        dao.addMovie(testMovie1);
        dao.addMovie(testMovie2);
        ArrayList<Movie> movies = dao.getMovies();
        assertTrue(movies.containsAll(Arrays.asList(testMovie1, testMovie2)));
    }

	
	  @Test public void testDeleteMovie() { 
		  dao.addMovie(testMovie1);
		  assertTrue(dao.deleteMovie(testMovie1));
		  assertFalse(dao.getMovies().contains(testMovie1));
}
	  
	  @Test public void testDeleteNonExistentMovie() {
		  assertFalse(dao.deleteMovie(testMovie2));
	  }
	 
}