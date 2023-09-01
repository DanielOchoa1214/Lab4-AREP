package org.arep.taller1;

import org.arep.taller1.apifacade.Cache;
import org.arep.taller1.apifacade.HttpConnection;
import org.junit.Test;
import static junit.framework.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FacadeTests {

    @Test
    public void testingGetMovies() throws IOException {
        // ARRANGE
        Map<String, String> correctInfo = new HashMap<>();
        correctInfo.put("It", "{\"Title\":\"It\",\"Year\":\"2017\",\"Rated\":\"R\",\"Released\":\"08 Sep 2017\",\"Runtime\":\"135 min\",\"Genre\":\"Horror\",\"Director\":\"Andy Muschietti\",\"Writer\":\"Chase Palmer, Cary Joji Fukunaga, Gary Dauberman\",\"Actors\":\"Bill Skarsgård, Jaeden Martell, Finn Wolfhard\",\"Plot\":\"In the summer of 1989, a group of bullied kids band together to destroy a shape-shifting monster, which disguises itself as a clown and preys on the children of Derry, their small Maine town.\",\"Language\":\"English, Hebrew\",\"Country\":\"United States, Canada\",\"Awards\":\"10 wins & 46 nominations\",\"Poster\":\"https://m.media-amazon.com/images/M/MV5BZDVkZmI0YzAtNzdjYi00ZjhhLWE1ODEtMWMzMWMzNDA0NmQ4XkEyXkFqcGdeQXVyNzYzODM3Mzg@._V1_SX300.jpg\",\"Ratings\":[{\"Source\":\"Internet Movie Database\",\"Value\":\"7.3/10\"},{\"Source\":\"Rotten Tomatoes\",\"Value\":\"86%\"},{\"Source\":\"Metacritic\",\"Value\":\"69/100\"}],\"Metascore\":\"69\",\"imdbRating\":\"7.3\",\"imdbVotes\":\"576,995\",\"imdbID\":\"tt1396484\",\"Type\":\"movie\",\"DVD\":\"19 Dec 2017\",\"BoxOffice\":\"$328,874,981\",\"Production\":\"N/A\",\"Website\":\"N/A\",\"Response\":\"True\"}");

        // ACT
        String searchedInfo = HttpConnection.getMovie("It");

        // ASSERT
        assertEquals(correctInfo.get("It"), searchedInfo);
    }

    @Test
    public void testingCacheConcurrency() throws InterruptedException {
        // ARRANGE
        List<Thread> threads = new ArrayList<>();
        threads.add(new ConnectionThread("It"));
        threads.add(new ConnectionThread("It"));
        threads.add(new ConnectionThread("It"));
        threads.add(new ConnectionThread("It"));

        String itInfo = "{\"Title\":\"It\",\"Year\":\"2017\",\"Rated\":\"R\",\"Released\":\"08 Sep 2017\",\"Runtime\":\"135 min\",\"Genre\":\"Horror\",\"Director\":\"Andy Muschietti\",\"Writer\":\"Chase Palmer, Cary Joji Fukunaga, Gary Dauberman\",\"Actors\":\"Bill Skarsgård, Jaeden Martell, Finn Wolfhard\",\"Plot\":\"In the summer of 1989, a group of bullied kids band together to destroy a shape-shifting monster, which disguises itself as a clown and preys on the children of Derry, their small Maine town.\",\"Language\":\"English, Hebrew\",\"Country\":\"United States, Canada\",\"Awards\":\"10 wins & 46 nominations\",\"Poster\":\"https://m.media-amazon.com/images/M/MV5BZDVkZmI0YzAtNzdjYi00ZjhhLWE1ODEtMWMzMWMzNDA0NmQ4XkEyXkFqcGdeQXVyNzYzODM3Mzg@._V1_SX300.jpg\",\"Ratings\":[{\"Source\":\"Internet Movie Database\",\"Value\":\"7.3/10\"},{\"Source\":\"Rotten Tomatoes\",\"Value\":\"86%\"},{\"Source\":\"Metacritic\",\"Value\":\"69/100\"}],\"Metascore\":\"69\",\"imdbRating\":\"7.3\",\"imdbVotes\":\"576,995\",\"imdbID\":\"tt1396484\",\"Type\":\"movie\",\"DVD\":\"19 Dec 2017\",\"BoxOffice\":\"$328,874,981\",\"Production\":\"N/A\",\"Website\":\"N/A\",\"Response\":\"True\"}";

        // ACT
        for (Thread t : threads) {
            t.start();
        }

        for (Thread t : threads) {
            t.join();
        }
        // ASSERT
        assertEquals(itInfo, Cache.getInstance().getCacheMemory().get("It"));
        assertEquals(1, Cache.getInstance().getCacheMemory().size());
    }
}
