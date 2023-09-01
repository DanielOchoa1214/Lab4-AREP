package org.arep.taller1.minispark;

public interface Service {
    /**
     * Method that will be called when calling a service
     * @param req Request object representing the HTTP request
     * @return String containing the response to the client
     */
    String handle(Request req);
}
