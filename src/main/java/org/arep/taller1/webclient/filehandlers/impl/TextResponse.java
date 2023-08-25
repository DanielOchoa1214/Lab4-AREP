package org.arep.taller1.webclient.filehandlers.impl;

import org.arep.taller1.webclient.filehandlers.ResponseInterface;

import java.io.*;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;

public class TextResponse implements ResponseInterface {
    private Socket clientSocket;
    private String fileType;
    private URI filePath;

    public TextResponse(Socket clientSocket, String fileType, URI filePath){
        this.clientSocket = clientSocket;
        this.fileType = fileType;
        try {
            this.filePath = new URI("." + filePath);
        }catch (URISyntaxException e){
            this.filePath = filePath;
        }

    }

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
