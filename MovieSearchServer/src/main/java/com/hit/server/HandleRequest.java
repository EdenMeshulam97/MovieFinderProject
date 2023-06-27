package com.hit.server;

import com.google.gson.Gson;
import com.hit.controller.MovieController;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class HandleRequest implements Runnable {
    private final Socket clientSocket; // Socket for client-server communication
    private final MovieController movieController; // Controller to handle requests

    // Constructor to initialize client socket and controller
    public HandleRequest(Socket clientSocket, MovieController movieController){
        this.movieController = movieController;
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        Gson jsonConverter = new Gson();
        String clientData;
        Request clientRequest;

        try {
            // Scanner for reading input stream from the client
            Scanner inputStreamReader = new Scanner(new InputStreamReader(clientSocket.getInputStream()));

            // PrintWriter for writing data to the output stream for the client
            PrintWriter outputStreamWriter = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

            // Check if the client sent any data
            if (inputStreamReader.hasNextLine()) {
                clientData = inputStreamReader.nextLine();

                // Check if the data sent by the client is not null and not empty
                if (clientData != null && !clientData.isEmpty()) {
                    // Convert the client data from JSON into a Request object
                    clientRequest = jsonConverter.fromJson(clientData, Request.class);

                    // Process the client request and get a response
                    Response serverResponse =  movieController.performAction(clientRequest);

                    // Convert the server response into a JSON string
                    String jsonResponse = jsonConverter.toJson(serverResponse);

                    // Write the JSON response back to the client
                    outputStreamWriter.println(jsonResponse);

                    // Flush the writer to make sure all data is sent
                    outputStreamWriter.flush();
                }
            }
        } catch (IOException e) {
            // Print the stack trace for any IOException
            e.printStackTrace();
        } finally {
            // Close the client socket
            try {
                if (clientSocket != null) {
                    clientSocket.close();
                }
            } catch (IOException e) {
                // Print the stack trace for any IOException while closing the socket
                e.printStackTrace();
            }
        }
    }
}
