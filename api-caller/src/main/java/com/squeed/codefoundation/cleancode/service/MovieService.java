package com.squeed.codefoundation.cleancode.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {

    @Autowired
    private RestTemplate restTemplate;

    /**
     *  http://www.omdbapi.com/?i=tt3896198&apikey=c626976c
     *  parameters:
     *         i=imdb ID
     *         t=title
     *         type=movie/series/episode
     *         y=year
     *         plot=short/full
     *         r=data type of return value
     */
    public List<Movie> getMovies(String imdbId, String title, String search, String type, int year, int plot, String returnType, int page){

        if (notEnoughParameters(search, imdbId, title)) {
            return null;
        }

        String url = "http://www.omdbapi.com/?apikey=c626976c";

        if (search == null) {

        } else {
            url += "&s=" + search;
            if (type != null) {
                if (type.equalsIgnoreCase("movie")) {
                    url += "&type=movie";
                } else if (type.equalsIgnoreCase("series")) {
                    url += "&type=series";
                } else if (type.equalsIgnoreCase("episode")) {
                    url += "&type=episode";
                }
            }

            if (year > 0) {
                url += "&y=" + new Integer(year).toString();
            }

            if (returnType != null) {
                url += "&r=" + returnType;
            }

            if (page > 0 && page <= 100) {
                url += "&page=" + page;
            }
        }

        if (imdbId != null) {
            url += "&i=" + imdbId;
        } else if (title != null) {
            url += "&t=" + title;
        }

        if (type != null) {
            if (type.equalsIgnoreCase("movie")) {
                url += "&type=movie";
            } else if (type.equalsIgnoreCase("series")) {
                url += "&type=series";
            } else if (type.equalsIgnoreCase("episode")) {
                url += "&type=episode";
            }
        }

        if (year > 0) {
            url += "&y=" + new Integer(year).toString();
        }

        if (plot == 0) {
            url += "&plot=short";
        } else {
            url += "&plot=full";
        }

        if (returnType != null) {
            url += "&r=" + returnType;
        }

        List<Movie> movies = new ArrayList<>();
        System.out.println(url);
        if (search == null) {
            ResponseEntity<Movie> responseEntity = restTemplate.getForEntity(
                    url, Movie.class);

            if (responseEntity.getBody().isResponse()) {
                movies.add(responseEntity.getBody());
            }
        } else {
            ResponseEntity<SearchResponse> responseEntity = restTemplate.getForEntity(
                    url, SearchResponse.class);

            movies = responseEntity.getBody().getSearch();

        }
        return movies;
    }

    private static boolean notEnoughParameters(String search, String imdbId, String title) {
        return search == null && imdbId == null && title == null;
    }
}
