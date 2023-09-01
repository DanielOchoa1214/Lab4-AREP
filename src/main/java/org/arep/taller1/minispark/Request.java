package org.arep.taller1.minispark;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;

public class Request {
    private String verb;
    private String body;
    private String path;
    private String query;

    public Request(String rawRequest) throws URISyntaxException {
        this.verb = rawRequest.split(" ")[0];
        URI uri = new URI(rawRequest.split(" ")[1]);
        this.path = uri.getPath();
        this.query = uri.getQuery();
        String[] requestLines = rawRequest.split("\n");
        this.body = requestLines[requestLines.length - 1];
    }

    public String getVerb() {
        return verb;
    }

    public void setVerb(String verb) {
        this.verb = verb;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
}
