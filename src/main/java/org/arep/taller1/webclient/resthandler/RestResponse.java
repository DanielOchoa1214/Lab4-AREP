package org.arep.taller1.webclient.resthandler;

import org.arep.taller1.apifacade.HttpConnection;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URI;
import java.net.URLDecoder;

/**
 * Class in charge of handling REST requests to the back end (Controller)
 * @author Daniel Ochoa
 */
public class RestResponse {

    /**
     * Method in charge of sending the response to the client when he searches a movie
     * @param clientSocket Socket where the server established communication with the client
     * @param path URI containing the GET request
     * @throws IOException Exception is throne in case the Backend method fails
     */
    public static void getMovieResponse(Socket clientSocket, URI path) throws IOException {
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        String outputLine;

        System.out.println(path);
        String movieTitle = URLDecoder.decode(path.getQuery().split("=")[1]);

        outputLine = "HTTP/1.1 200 OK \r\n" +
                "Content-Type: application/json \r\n" +
                "\r\n";
        outputLine += HttpConnection.getMovie(movieTitle);

        out.println(outputLine);

        out.close();
        clientSocket.close();
    }
}
