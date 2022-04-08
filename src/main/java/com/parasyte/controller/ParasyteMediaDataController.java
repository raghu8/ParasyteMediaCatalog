package com.parasyte.controller;

import com.parasyte.apiDao.MovieDbApiCall;
import com.parasyte.miscservices.EmailNotifications;
import com.parasyte.model.MediaSearchResults;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class ParasyteMediaDataController {
    @Autowired
    private MovieDbApiCall movieDbApiCall;

    @Autowired
    private EmailNotifications notificationService;


    //TODO: Create a util function that creates a list of different combinations of movie/show name (different combination of spaces)
    @GetMapping("/hello")
    public String testCall() {
        return "Hello World!!";
    }

    @GetMapping("/movieDetails/movieName/{movieName}")
    public MediaSearchResults movieDb(@PathVariable("movieName") String movieName) throws IOException {
        return movieDbApiCall.callData(movieName);
    }

    @GetMapping("/emailResults/recipientEmail/{recipientEmail}/movieName/{movieName}")
    public String emailResults(@PathVariable("recipientEmail") String recipientEmail, @PathVariable("movieName") String movieName) throws IOException, TemplateException {
        MediaSearchResults movieDetails = new MediaSearchResults();
        movieDetails = movieDbApiCall.callData(movieName);
        String movieOriginalTitle = movieDetails.getResults().get(0).original_title;
        String moviePoster = "https://image.tmdb.org/t/p/w500" + movieDetails.getResults().get(0).poster_path;
        String movieOverview = movieDetails.getResults().get(0).overview;
        System.out.println(movieOriginalTitle);

        notificationService.sendEmail(recipientEmail, movieOriginalTitle, moviePoster, movieOverview);
        return movieOriginalTitle;
    }
}
