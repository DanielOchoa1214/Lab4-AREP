package org.arep.taller1.webclient.filehandlers.impl;

import org.arep.taller1.webclient.filehandlers.ResponseInterface;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Class in charge of responding with the default error page
 * @author Daniel Ochoa
 */
public class ErrorResponse implements ResponseInterface {

    private Socket clientSocket;

    /**
     * Constructor of the ErrorResponse class
     * @param clientSocket Socket where the server established communication with the client
     */
    public ErrorResponse(Socket clientSocket){
        this.clientSocket = clientSocket;
    }

    /**
     * Method that responds to an incorrect or not valid request to the server
     * @throws IOException In case something goes wrong during the streaming of the response
     */
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
