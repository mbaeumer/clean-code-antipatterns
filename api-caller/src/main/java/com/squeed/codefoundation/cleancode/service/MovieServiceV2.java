package com.squeed.codefoundation.cleancode.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class MovieServiceV2 {
    @Autowired
    private RestTemplate restTemplate;


    public List<Movie> getMovies(String i, String title, String search,
                                 String type, int year, int plot, String returnType, int page){

        String url = getUrl(i, title, search, type, year, plot, returnType, page);
        if (url == null) return null;

        List<Movie> movies = getOmdbMovies(search, url);
        return movies;
    }

    private List<Movie> getOmdbMovies(String search, String url) {
        List<Movie> movies = new ArrayList<>();
        System.out.println(url);
        if (search == null) {
            ResponseEntity<Movie> responseEntity = restTemplate.getForEntity(
                    url, Movie.class);

            int status = responseEntity.getStatusCode().value();

            if (responseEntity.getBody().isResponse()) {
                movies.add(responseEntity.getBody());
            } else {
                // something went wrong...
            }
        }else{
            ResponseEntity<SearchResponse> responseEntity = restTemplate.getForEntity(
                    url, SearchResponse.class);
            int status = responseEntity.getStatusCode().value();
            movies = responseEntity.getBody().getSearch();
        }
        return movies;
    }

    private String getUrl(String i, String title, String search, String type, int year, int plot, String returnType, int page) {
        String url = "http://www.omdbapi.com/?apikey=c626976c";

        if (search == null) {
            if (i == null && title == null) {
                return null;
            }
        }else{
            url += "&s=" + search;
            if (type != null){
                if (type.equalsIgnoreCase("movie")){
                    url += "&type=movie";
                }else if (type.equalsIgnoreCase("series")){
                    url += "&type=series";
                }else if (type.equalsIgnoreCase("episode")){
                    url += "&type=episode";
                }
            }

            if (year > 0){
                url += "&y=" + new Integer(year).toString();
            }

            if (returnType != null){
                url += "&r=" + returnType;
            }

            if (page > 0 && page <= 100){
                url += "&page=" + page;
            }
        }

        if (i != null){
            url += "&i=" + i;
        }else if (title != null){
            url += "&t=" + title;
        }

        if (type != null){
            if (type.equalsIgnoreCase("movie")){
                url += "&type=movie";
            }else if (type.equalsIgnoreCase("series")){
                url += "&type=series";
            }else if (type.equalsIgnoreCase("episode")){
                url += "&type=episode";
            }
        }

        if (year > 0){
            url += "&y=" + new Integer(year).toString();
        }

        if (plot == 0){
            url += "&plot=short";
        }else{
            url += "&plot=full";
        }

        if (returnType != null){
            url += "&r=" + returnType;
        }
        return url;
    }
}
