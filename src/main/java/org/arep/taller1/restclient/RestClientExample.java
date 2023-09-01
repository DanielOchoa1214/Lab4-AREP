package org.arep.taller1.restclient;

import org.arep.taller1.apifacade.HttpConnection;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.arep.taller1.minispark.MiniSpark.*;
public class RestClientExample {
    public static void main(String[] args) throws IOException, URISyntaxException {
        get("/hello", (req, res) -> {
            try {
                String movieTitle = req.getQuery().split("=")[1];
                return HttpConnection.getMovie(movieTitle);
            } catch (IOException e){
                return "Movie not found :(";
            }
        });

        post("/hello", (req, res) -> {
            JSONObject body = req.getBody();
            try {
                String movieTitle = (String) body.get("name");
                return HttpConnection.getMovie(movieTitle);
            } catch (IOException e){
                return "Movie not found :(";
            }
        });

        start();

    }
}
