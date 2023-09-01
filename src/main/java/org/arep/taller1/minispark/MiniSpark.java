package org.arep.taller1.minispark;

import org.arep.taller1.webclient.HttpServer;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

public class MiniSpark {
    private static final Map<String, Service> GET_SERVICES = new HashMap<>();
    private static final Map<String, Service> POST_SERVICES = new HashMap<>();

    /**
     * Method that saves a GET Service and maps it to an endpoint
     * @param endpoint Endpoint of the Service
     * @param lambda Function to be run when the endpoint is called
     */
    public static void get(String endpoint, Service lambda){
        GET_SERVICES.put(endpoint, lambda);
    }

    /**
     * Method that saves a POST Service and maps it to an endpoint
     * @param endpoint Endpoint of the Service
     * @param lambda Function to be run when the endpoint is called
     */
    public static void post(String endpoint, Service lambda){
        POST_SERVICES.put(endpoint, lambda);
    }

    /**
     * Method that searches if a Service has been declared and returns it
     * @param endpoint Endpoint to the service
     * @param verb Verb that was used to declare the service (GET and POST are the only valid values for now)
     * @return The Service mapped to the endpoint if it was found, otherwise null
     */
    public static Service search(String endpoint, String verb){
        switch (verb){
            case "GET":
                return GET_SERVICES.get(endpoint);
            case "POST":
                return POST_SERVICES.get(endpoint);
        }
        return null;
    }

    /**
     * Method that starts the server
     * @throws IOException In case some I/O functionality failed
     * @throws URISyntaxException In case some URI creation failed
     */
    public static void start() throws IOException, URISyntaxException {
        HttpServer.start();
    }
}
