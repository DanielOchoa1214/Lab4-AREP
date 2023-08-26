package org.arep.taller1.webclient;

import org.arep.taller1.apifacade.HttpConnection;

import java.net.*;
import java.io.*;
import java.util.Arrays;
import java.util.List;

import org.arep.taller1.webclient.filehandlers.ResponseInterface;
import org.arep.taller1.webclient.filehandlers.impl.ErrorResponse;
import org.arep.taller1.webclient.filehandlers.impl.ImageResponse;
import org.arep.taller1.webclient.filehandlers.impl.TextResponse;
import org.arep.taller1.webclient.resthandler.RestResponse;
import org.json.*;

/**
 * Web Client, this class is responsable for the creation a Socket between the client and the server and delivering any
 * and all requests the client may need
 * @author Daniel Ochoa
 * @author Daniel Benavides
 */
public class HttpServer {

    private static ResponseInterface responseInterface;

    private static final List<String> supportedImgFormats = Arrays.asList("jpg", "png", "jpeg");

    private static final List<String> supportedTextFormats = Arrays.asList("html", "css", "js");

    /**
     * This method initiates the server, accepts and administrate client connections and handles the request of the client
     * @param args Default arguments needed to make a main method
     * @throws IOException Exception is thrown if something goes wrong during the handling if the connections
     */
    public static void main(String[] args) throws IOException, URISyntaxException {

        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(35000);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 35000.");
            System.exit(1);
        }
        boolean running = true;
        while (running) {
            Socket clientSocket = null;
            try {
                System.out.println("Listo para recibir ...");
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }

            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            String inputLine = in.readLine();
            String path = inputLine.split(" ")[1];
            URI resourcePath = new URI("/target/classes/public" + path);
            System.out.println("Received: " + inputLine);

            if(resourcePath.getQuery() != null){
                RestResponse.sendRestResponse(clientSocket, resourcePath);
            } else {
                sendResponse(resourcePath, clientSocket);
            }

            in.close();
        }
        serverSocket.close();
    }

    private static void sendResponse(URI resourcePath, Socket clientSocket) throws IOException, URISyntaxException {
        char lastChar = resourcePath.getPath().charAt(resourcePath.getPath().length() - 1);
        String fileType = getFileType(resourcePath);
        if (lastChar == '/') {
            responseInterface = new TextResponse(clientSocket, "html", new URI(resourcePath.getPath() + "/index.html"));
        } else if (!fileExists(resourcePath)){
            responseInterface = new ErrorResponse(clientSocket);
        } else if (isImage(resourcePath)) {
            responseInterface = new ImageResponse(clientSocket, fileType, resourcePath);
        } else if (isText(resourcePath)) {
            responseInterface = new TextResponse(clientSocket, fileType, resourcePath);
        } else {
            responseInterface = new ErrorResponse(clientSocket);
        }
        responseInterface.sendResponse();
    }

    private static String getFileType(URI path){
        String fileFormat = "";
        try {
            fileFormat = path.getPath().split("\\.")[1];
        } catch (ArrayIndexOutOfBoundsException e){}
        return fileFormat;
    }

    private static boolean isText(URI path){
        String fileFormat = path.getPath().split("\\.")[1];
        return supportedTextFormats.contains(fileFormat);
    }

    private static boolean isImage(URI path){
        String fileFormat = path.getPath().split("\\.")[1];
        return supportedImgFormats.contains(fileFormat);
    }

    private static boolean fileExists(URI path) {
        File file = new File(System.getProperty("user.dir") + path);
        return file.exists();
    }
}
