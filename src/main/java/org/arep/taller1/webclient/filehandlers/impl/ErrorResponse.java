package org.arep.taller1.webclient.filehandlers.impl;

import org.arep.taller1.webclient.filehandlers.ResponseInterface;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class ErrorResponse implements ResponseInterface {

    private Socket clientSocket;

    public ErrorResponse(Socket clientSocket){
        this.clientSocket = clientSocket;
    }
    @Override
    public void sendResponse() throws IOException {
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        String outputLine;

        outputLine = "HTTP/1.1 404 Not Found \r\n" +
                "Content-Type: text/html \r\n" +
                "\r\n";
        outputLine += ResponseInterface.readFile("./target/classes/public/NotFound.html");

        out.println(outputLine);

        out.close();
        clientSocket.close();
    }
}
