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
    public List<Movie> getMovies(String imdbId, String title, String search, String type, int year, int plot, String returnType, int page) {
        return getMovies(new OmdbApiParameters(imdbId, title, search, type, year, plot, returnType, page));
    }

    /**
     *  http://www.omdbapi.com/?i=tt3896198&apikey=c626976c
     *  parameters:
     *         i=imdb ID
     *         t=title
     *         type=movie/series/episode
     *         y=year
     *         plot=short/full
     *         r=data type of return value
     * @param omdbApiParameters
     */
    public List<Movie> getMovies(OmdbApiParameters omdbApiParameters){

        if (notEnoughParameters(omdbApiParameters.getSearch(), omdbApiParameters.getImdbId(), omdbApiParameters.getTitle())) {
            return null;
        }

        OmdbApi api = new OmdbApi(omdbApiParameters);

        String url = api.getUrl();

        List<Movie> movies = fetchMovies(url, omdbApiParameters);

        return movies;
    }

    private List<Movie> fetchMovies(String url, OmdbApiParameters omdbApiParameters) {
        List<Movie> movies = new ArrayList<>();
        if (omdbApiParameters.getSearch() != null) {
            ResponseEntity<SearchResponse> responseEntity = restTemplate.getForEntity(
                    url, SearchResponse.class);

            movies = responseEntity.getBody().getSearch();
        } else {
            ResponseEntity<Movie> responseEntity = restTemplate.getForEntity(
                    url, Movie.class);

            if (responseEntity.getBody().isResponse()) {
                movies.add(responseEntity.getBody());
            }
        }
        return movies;
    }

    private static boolean notEnoughParameters(String search, String imdbId, String title) {
        return search == null && imdbId == null && title == null;
    }
}
