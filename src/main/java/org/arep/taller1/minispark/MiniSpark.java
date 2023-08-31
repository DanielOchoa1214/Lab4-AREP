package org.arep.taller1.minispark;

import org.arep.taller1.webclient.HttpServer;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

public class MiniSpark {
    private static final Map<String, Service> services = new HashMap<>();

    public static void get(String endpoint, Service lambda){
        services.put(endpoint, lambda);
    }

    public static Service search(String endpoint){
        return services.get(endpoint);
    }

    public static void start() throws IOException, URISyntaxException {
        HttpServer.start();
    }
}
