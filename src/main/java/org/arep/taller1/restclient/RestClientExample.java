package org.arep.taller1.restclient;

import org.arep.taller1.apifacade.HttpConnection;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.arep.taller1.minispark.MiniSpark.*;
public class RestClientExample {
    public static void main(String[] args) throws IOException, URISyntaxException {
        get("/hello", query -> {
            String movieTitle = query.split("=")[1];
            try {
                return HttpConnection.getMovie(movieTitle);
            } catch (IOException e){
                return "Movie not found :(";
            }
        });

        start();

    }
}
