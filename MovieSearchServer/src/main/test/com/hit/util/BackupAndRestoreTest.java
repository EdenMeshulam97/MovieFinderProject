package com.hit.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

@TestMethodOrder(OrderAnnotation.class)
public class BackupAndRestoreTest {
    private BackupAndRestore backupAndRestore;
    private String sourceFilePath;
    private String backupFilePath;

    @BeforeEach
    public void setUp() {
        backupAndRestore = new BackupAndRestore();
        sourceFilePath = "src/main/resources/DataBase.txt";
        backupFilePath = "src/main/resources/TestDataBase.txt";
    }

    @Test
    @Order(1)
    public void testBackup() throws IOException, InterruptedException {
        backupAndRestore.backup(sourceFilePath, backupFilePath,0,10000);
        Thread.sleep(1000);
        assertTrue(Files.exists(Paths.get(backupFilePath)));
        byte[] sourceFile = Files.readAllBytes(Paths.get(sourceFilePath));
        byte[] backupFile = Files.readAllBytes(Paths.get(backupFilePath));
		assertTrue(Arrays.equals(sourceFile, backupFile));
    }

    @Test
    @Order(2)
    public void testRestore() {
        Object restored = backupAndRestore.restore(backupFilePath);

        assertNotNull(restored);
        assertTrue(restored instanceof File);
    }
}
