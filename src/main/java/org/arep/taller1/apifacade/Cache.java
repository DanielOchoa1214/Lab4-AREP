package org.arep.taller1.apifacade;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * This class it's a simple abstraction of a cache that's concurrency resistant
 * @author Daniel Ochoa
 */
public class Cache {
    private final Map<String, String> cacheMemory;
    private static Cache cache = null;

    /**
     * Method following the singleton pattern to get only one instance of a Cache object
     * @return The only Cache object created in the project
     */
    public static Cache getInstance(){
        if(cache != null){
            return cache;
        }
        cache = new Cache();
        return cache;
    }

    /**
     * Constructor of the Cache class
     */
    private Cache(){
        cacheMemory = new ConcurrentHashMap<>();
    }

    /**
     * This method takes in the information of the movie to be saved and stores it in cache
     * @param title Title of the movie searched
     * @param movieInfo Iformation of the movie in JSON format gotten by the external API
     */
    public void save(String title, String movieInfo){
        cacheMemory.put(title, movieInfo);
    }

    /**
     * This method checks if the movie has been searched before i.e. the title is in the cache
     * @param title Title of the movie we are checking
     * @return True if the movie is in the cache, false otherwise
     */
    public boolean searchedBefore(String title){
        return cacheMemory.containsKey(title);
    }

    /**
     * This method gets the information of the movie from the cache
     * @param title Title of the movie we want the information of
     * @return String of the JSON object containing the information of the movie
     */
    public String getMovie(String title){
        return cacheMemory.get(title);
    }

    /**
     * Get method of the cache memory attribute
     * @return The cached movies in memory
     */
    public Map<String, String> getCacheMemory() {
        return cacheMemory;
    }
}
