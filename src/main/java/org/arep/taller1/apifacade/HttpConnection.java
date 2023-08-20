package org.arep.taller1.apifacade;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Facade API class in charge with connecting to the external API
 * @author Daniel Ochoa
 * @author Daniel Benavides
 */
public class HttpConnection {

    private static final String USER_AGENT = "Mozilla/5.0";
    private static final String GET_URL = "http://www.omdbapi.com/?apikey=b48287fc&t=";
    private static final Cache cache = Cache.getInstance();

    /**
     * GET interface that makes the connection to the external API and returns the information of the movie searched if
     * necessary
     * @param movieTitle Title of the movie that was searched
     * @return Information of the movie
     * @throws IOException Exception id thrown if the URL if malformed or the connection is refused
     */
    public static String getMovie(String movieTitle) throws IOException {
        if(cache.searchedBefore(movieTitle)){
            System.out.println("Se uso CACHE");
            return cache.getMovie(movieTitle);
        }

        HttpURLConnection con = makeGetRequest(movieTitle);

        StringBuffer response = new StringBuffer();
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);

        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            response = buildResponse(con);
            System.out.println(response); // print result
        } else {
            System.out.println("GET request not worked");
        }

        System.out.println("GET DONE");
        cache.save(movieTitle, response.toString());
        return response.toString();
    }

    /*
    Method that establishes the connection to the external API
     */
    private static HttpURLConnection makeGetRequest(String movieTitle) throws IOException{
        URL obj = new URL(GET_URL + movieTitle);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);
        return con;
    }

    /*
    Method that takes in the raw response from the API and parses it to a more easily handled StringBuffer
     */
    private static StringBuffer buildResponse(HttpURLConnection con) throws IOException {
        StringBuffer response = new StringBuffer();
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response;
    }
}