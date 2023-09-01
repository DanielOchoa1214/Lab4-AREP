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

    public Request(String rawRequest) throws URISyntaxException {
        this.verb = rawRequest.split(" ")[0];
        URI uri = new URI(rawRequest.split(" ")[1]);
        this.path = uri.getPath();
        this.query = uri.getQuery();
        buildBody(rawRequest);
    }

    private void buildBody(String rawRequest){
        try {
            String[] requestLines = rawRequest.split("\n");
            this.body = new JSONObject(requestLines[requestLines.length - 1]);
        } catch (JSONException e){
            this.body = null;
        }
    }

    public String getVerb() {
        return verb;
    }

    public void setVerb(String verb) {
        this.verb = verb;
    }

    public JSONObject getBody() {
        return body;
    }

    public void setBody(JSONObject body) {
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
