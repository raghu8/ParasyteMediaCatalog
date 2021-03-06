package com.parasyte.apiDao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.parasyte.model.MediaSearchResult;
import com.parasyte.model.MediaSearchResults;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class MovieDbApiCall {

    public MediaSearchResults callData(String movieName)throws IOException {
        String movieUrl = "https://api.themoviedb.org/3/search/movie?api_key=15d2ea6d0dc1d476efbca3eba2b9bbfb&query="+movieName;
        URL url = new URL(movieUrl);
        ObjectMapper mapper = new ObjectMapper();
        HttpURLConnection conection = (HttpURLConnection) url.openConnection();
        conection.setRequestMethod("GET");
        StringBuilder result = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader( new InputStreamReader(conection.getInputStream()));
            result.append(reader.readLine());
        }catch (Exception e){
            System.out.println(e);
        }

        MediaSearchResults msrObject = mapper.readValue(result.toString(), MediaSearchResults.class);
        return msrObject;
    }
    /*
     * 1. TODO: Take the movie/show name list generated by util function and check it against db. If not preset then make api call
     * 2. TODO: Find a common api to search for shows and movies, if not possible create seprate functions for show search and movie search
     * 3. TODO: Find another api to show where the movie/show is hosted.
     * 4. TODO: Take the results of the api call(s) and store store relavent information about the movie/show in the database.
     */

}
