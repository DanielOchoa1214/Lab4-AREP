package org.arep.taller1.webclient.resthandler;

import org.arep.taller1.apifacade.HttpConnection;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URI;
import java.net.URLDecoder;

public class RestResponse {

    public static void sendRestResponse(Socket clientSocket, URI path) throws IOException {
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
