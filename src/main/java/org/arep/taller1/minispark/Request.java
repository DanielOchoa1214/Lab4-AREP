package org.arep.taller1.minispark;


import java.net.URI;
import java.net.URISyntaxException;

import org.json.JSONException;
import org.json.JSONObject;

public class Request {
    private String verb;
    private JSONObject body;
    private String path;
    private String query;

    /**
     * Contructor of the Request class
     * @param rawRequest String containing the raw HTTP request made to the server
     * @throws URISyntaxException This error will never be thrown
     */
    public Request(String rawRequest) throws URISyntaxException {
        this.verb = rawRequest.split(" ")[0];
        URI uri = new URI(rawRequest.split(" ")[1]);
        this.path = uri.getPath();
        this.query = uri.getQuery();
        buildBody(rawRequest);
    }

    /*
    Method that takes the raw request and get the body if it has one
     */
    private void buildBody(String rawRequest){
        try {
            String[] requestLines = rawRequest.split("\n");
            this.body = new JSONObject(requestLines[requestLines.length - 1]);
        } catch (JSONException e){
            this.body = null;
        }
    }

    /**
     * Get the verb of the request
     * @return Verb of the request
     */
    public String getVerb() {
        return verb;
    }


    /**
     * Get the body of the request
     * @return Body of the request
     */
    public JSONObject getBody() {
        return body;
    }

    /**
     * Get the path of the request
     * @return Path of the request
     */
    public String getPath() {
        return path;
    }

    /**
     * Get the query of the request
     * @return Query of the request
     */
    public String getQuery() {
        return query;
    }
}
