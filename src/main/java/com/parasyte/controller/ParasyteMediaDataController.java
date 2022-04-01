package com.parasyte.controller;

import com.parasyte.apiDao.MovieDbApiCall;
import com.parasyte.model.MediaSearchResult;
import com.parasyte.model.MediaSearchResults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class ParasyteMediaDataController {
    @Autowired
    private MovieDbApiCall movieDbApiCall;

    @Autowired
    private MediaSearchResults mediaSearchResults;

    //TODO: Create a util function that creates a list of different combinations of movie/show name (different combination of spaces)
    @GetMapping("/hello")
    public String testCall(){
        return "Hello World!!";
    }

    @GetMapping("/movieDetails")
    public MediaSearchResults movieDb(@RequestParam String movieName) throws IOException {
        return movieDbApiCall.callData(movieName);
    }
}
