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

    public static void sendResponse(Socket clientSocket, String response) throws IOException {
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        String outputLine;


        outputLine = "HTTP/1.1 200 OK \r\n" +
                "Content-Type: application/json \r\n" +
                "\r\n";
        outputLine += response;

        out.println(outputLine);

        out.close();
        clientSocket.close();
    }
}
