package org.arep.taller1.webclient;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.*;
import java.io.*;

import org.arep.taller1.minispark.MiniSpark;
import org.arep.taller1.minispark.Request;
import org.arep.taller1.minispark.Service;
import org.arep.taller1.minispring.ComponentLoader;
import org.arep.taller1.webclient.filehandlers.FileHandler;
import org.arep.taller1.webclient.resthandler.RestResponse;

/**
 * Web Client, this class is responsable for the creation a Socket between the client and the server and delivering any
 * and all requests the client may need
 * @author Daniel Ochoa
 * @author Daniel Benavides
 */
public class HttpServer {

    /**
     * This method initiates the server, accepts and administrate client connections and handles the request of the client
     * @throws IOException Exception is thrown if something goes wrong during the handling if the connections
     */
    public static void main(String[] args) throws IOException, URISyntaxException, InvocationTargetException, IllegalAccessException, ClassNotFoundException {
        ComponentLoader.loadComponents();
        ServerSocket serverSocket = startServerSocket();
        while (true) {
            Socket clientSocket = startClientSocket(serverSocket);

            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            StringBuilder rawRequest = getRawResponse(in);
            sendSpringSerponse(clientSocket, rawRequest.toString());

            in.close();
        }
    }

    /*
    Method that starts the server in port 35000
     */
    private static ServerSocket startServerSocket(){
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(35000);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 35000.");
            System.exit(1);
        }
        return serverSocket;
    }

    /*
    Method that accepts and handles connections to the server socket
     */
    private static Socket startClientSocket(ServerSocket serverSocket){
        Socket clientSocket = null;
        try {
            System.out.println("Listo para recibir ...");
            clientSocket = serverSocket.accept();
        } catch (IOException e) {
            System.err.println("Accept failed.");
            System.exit(1);
        }
        return clientSocket;
    }

    /*
    Method that reads the request from the buffer and creates a String to use it
     */
    private static StringBuilder getRawResponse(BufferedReader in) throws IOException {
        StringBuilder rawRequest = new StringBuilder();
        rawRequest.append(in.readLine()).append("\n");
        while (in.ready()) {
            rawRequest.append((char) in.read());
        }
        return rawRequest;
    }

    /*
    Method that send the appropriate response to the request
     */
    private static void sendSparkResponse(Socket clientSocket, String rawRequest) throws IOException, URISyntaxException {
        System.out.println("Received: " + rawRequest.split("\n")[0]);
        String method = rawRequest.split(" ")[0];
        String path = rawRequest.split(" ")[1];
        URI restPath = new URI(path);
        URI resourcePath = new URI("/target/classes/public" + path);
        Service service = MiniSpark.search(restPath.getPath(), method);

        if(service != null){
            Request req = new Request(rawRequest);
            String response = service.handle(req);
            RestResponse.sendResponse(clientSocket, response);
        } else {
            FileHandler.sendResponse(resourcePath, clientSocket);
        }
    }

    private static void sendSpringSerponse(Socket clientSocket, String rawRequest) throws IOException, URISyntaxException, InvocationTargetException, IllegalAccessException {
        System.out.println("Received: " + rawRequest.split("\n")[0]);
        String method = rawRequest.split(" ")[0];
        String path = rawRequest.split(" ")[1];
        URI restPath = new URI(path);
        URI resourcePath = new URI("/target/classes/public" + path);

        Method service = ComponentLoader.search(restPath.getPath(), method);

        if(service != null){
            Request req = new Request(rawRequest);
            String response = ComponentLoader.execute(service, req);
            RestResponse.sendResponse(clientSocket, response);
        } else {
            FileHandler.sendResponse(resourcePath, clientSocket);
        }


    }
}
