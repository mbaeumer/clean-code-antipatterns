package com.squeed.codefoundation.cleancode.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class MovieService {

    @Autowired
    private RestTemplate restTemplate;


    public String getMovies(String i, String title, String search, String type, int year, int plot, String returnType, int page){

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
        //http://www.omdbapi.com/?i=tt3896198&apikey=c626976c
        /* parameters:
        i=imdb ID
        t=title
        type=movie/series/episode
        y=year
        plot=short/full
        r=data type of return value
         */

        System.out.println(url);
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(
                url, String.class);

        int status = responseEntity.getStatusCode().value();

        if (responseEntity.getBody().contains("\"Response\":\"False\"")){
            // something went wrong...

        }else{

        }
        System.out.println(status);
        System.out.println(responseEntity.getBody());
        return responseEntity.getBody();
    }
}
