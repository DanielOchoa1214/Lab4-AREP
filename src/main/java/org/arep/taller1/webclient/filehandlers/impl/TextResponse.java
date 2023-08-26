package org.arep.taller1.webclient.filehandlers.impl;

import org.arep.taller1.webclient.filehandlers.ResponseInterface;

import java.io.*;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;

/**
 * Class in charge of responding with a text file solicited
 * @author Daniel Ochoa
 */
public class TextResponse implements ResponseInterface {
    private Socket clientSocket;
    private String fileType;
    private URI filePath;

    /**
     * Constructor of the TextResponse Class
     * @param clientSocket Socket where the server established communication with the client
     * @param fileType Type of file the client solicited
     * @param filePath Path to the file solicited
     */
    public TextResponse(Socket clientSocket, String fileType, URI filePath){
        this.clientSocket = clientSocket;
        this.fileType = fileType;
        try {
            this.filePath = new URI("." + filePath);
        }catch (URISyntaxException e){
            this.filePath = filePath;
        }

    }

    /**
     * Method that responds to a text based file request
     * @throws IOException In case something goes wrong during the streaming of the response
     */
    @Override
    public void sendResponse() throws IOException {
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        String outputLine;

        outputLine = "HTTP/1.1 200 OK \r\n" +
                    "Content-Type: " + getMimeType() + " \r\n" +
                    "\r\n";
        outputLine += ResponseInterface.readFile(filePath.getPath());

        out.println(outputLine);

        out.close();
        clientSocket.close();
    }

    /*
     * Method that according to the file type gets the MIME type
     */
    private String getMimeType(){
        switch (fileType){
            case "js":
                return "text/javascript";
            case "css":
                return "text/css";
            case "html":
                return "text/html";
        }
        return "";
    }
}
