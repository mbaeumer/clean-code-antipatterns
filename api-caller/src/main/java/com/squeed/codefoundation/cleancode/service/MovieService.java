package com.squeed.codefoundation.cleancode.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {

    public static final String OMDB_HOST_URL = "http://www.omdbapi.com/";
    public static final String APIKEY = "apikey=c626976c";

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

        String url = getUrl(omdbApiParameters);

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

    private String getUrl(OmdbApiParameters omdbApiParameters) {
        String url = OMDB_HOST_URL + "?" + APIKEY;

        if (omdbApiParameters.getSearch() != null) {
            url += "&s=" + omdbApiParameters.getSearch();
            if (omdbApiParameters.getType() != null) {
                if (omdbApiParameters.getType().equalsIgnoreCase("movie")) {
                    url += "&type=movie";
                } else if (omdbApiParameters.getType().equalsIgnoreCase("series")) {
                    url += "&type=series";
                } else if (omdbApiParameters.getType().equalsIgnoreCase("episode")) {
                    url += "&type=episode";
                }
            }

            if (omdbApiParameters.getYear() > 0) {
                url += "&y=" + new Integer(omdbApiParameters.getYear()).toString();
            }

            if (omdbApiParameters.getReturnType() != null) {
                url += "&r=" + omdbApiParameters.getReturnType();
            }

            if (omdbApiParameters.getPage() > 0 && omdbApiParameters.getPage() <= 100) {
                url += "&page=" + omdbApiParameters.getPage();
            }
        }

        if (omdbApiParameters.getImdbId() != null) {
            url += "&i=" + omdbApiParameters.getImdbId();
        } else if (omdbApiParameters.getTitle() != null) {
            url += "&t=" + omdbApiParameters.getTitle();
        }

        if (omdbApiParameters.getType() != null) {
            if (omdbApiParameters.getType().equalsIgnoreCase("movie")) {
                url += "&type=movie";
            } else if (omdbApiParameters.getType().equalsIgnoreCase("series")) {
                url += "&type=series";
            } else if (omdbApiParameters.getType().equalsIgnoreCase("episode")) {
                url += "&type=episode";
            }
        }

        if (omdbApiParameters.getYear() > 0) {
            url += "&y=" + new Integer(omdbApiParameters.getYear()).toString();
        }

        if (omdbApiParameters.getPlot() == 0) {
            url += "&plot=short";
        } else {
            url += "&plot=full";
        }

        if (omdbApiParameters.getReturnType() != null) {
            url += "&r=" + omdbApiParameters.getReturnType();
        }

        return url;
    }

    private static boolean notEnoughParameters(String search, String imdbId, String title) {
        return search == null && imdbId == null && title == null;
    }
}
