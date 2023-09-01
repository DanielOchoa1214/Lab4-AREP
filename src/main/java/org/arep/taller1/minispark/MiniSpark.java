package org.arep.taller1.minispark;

import org.arep.taller1.webclient.HttpServer;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

public class MiniSpark {
    private static final Map<String, Service> GET_SERVICES = new HashMap<>();
    private static final Map<String, Service> POST_SERVICES = new HashMap<>();

    public static void get(String endpoint, Service lambda){
        GET_SERVICES.put(endpoint, lambda);
    }

    public static void post(String endpoint, Service lambda){
        POST_SERVICES.put(endpoint, lambda);
    }

    public static Service search(String endpoint, String verb){
        switch (verb){
            case "GET":
                return GET_SERVICES.get(endpoint);
            case "POST":
                return POST_SERVICES.get(endpoint);
        }
        return null;
    }

    public static void start() throws IOException, URISyntaxException {
        HttpServer.start();
    }
}
