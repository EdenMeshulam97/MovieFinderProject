package com.hit.server;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.hit.controller.MovieController;

public class Server implements PropertyChangeListener, Runnable {
	
    // Use volatile to ensure multiple threads handle the server state correctly.
    private volatile boolean isRunning = false;
    private int port;
    private ServerSocket serverSocket;
    private ExecutorService executor;
    private MovieController controller;
	
    public Server(int port) {
        this.port = port;
    }
	
    @Override
    public void run() {
        try {
            // Initialize the controller and the executor.
            controller = new MovieController();
            executor = Executors.newFixedThreadPool(3);
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        
        // Server loop.
        while (isRunning) {
            try {
                // Accept new client connections and handle them.
                Socket clientSocket = serverSocket.accept();
                executor.execute(new HandleRequest(clientSocket, controller));
            } catch (Exception e) {
                // Gracefully handle exceptions.
                System.out.println("The server stopped listening to clients");
                break;
            }
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        String action = (String) evt.getNewValue();
        switch (action) {
            case "start":
                // Start the server if it isn't already running.
                if (!isRunning) {
                    isRunning = true;
                    new Thread(this).start();
                } else {
                    System.out.println("Server is already ON");
                }
                break;
            case "stop":
                // Stop the server if it is running.
                if (!isRunning) {
                    System.out.println("Server is already OFF");
                } else {
                    try {
                        isRunning = false;
                        serverSocket.close();
                    } catch (Exception e) {
                        System.out.println("Server off");
                    }
                }
                break;
            default:
                System.out.println("Not a valid command");
                break;
        }
    }
}
