package org.arep.taller1.springrestclient;

import org.arep.taller1.apifacade.HttpConnection;
import org.arep.taller1.minispark.Request;
import org.arep.taller1.minispring.Component;
import org.arep.taller1.minispring.GetMapping;
import org.arep.taller1.minispring.PostMapping;
import org.json.JSONObject;

import java.io.IOException;

@Component
public class RestClientExample {

    @GetMapping("/hello")
    public static String getMovie(Request req){
        try {
            String movieTitle = req.getQuery().split("=")[1];
            return HttpConnection.getMovie(movieTitle);
        } catch (IOException e){
            return "Movie not found :(";
        }
    }

    @PostMapping("/helloPost")
    public static String postMovie(Request req){
        JSONObject body = req.getBody();
        try {
            String movieTitle = (String) body.get("name");
            return HttpConnection.getMovie(movieTitle);
        } catch (IOException e){
            return "Movie not found :(";
        }
    }
}
