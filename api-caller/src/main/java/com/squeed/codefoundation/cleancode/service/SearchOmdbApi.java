package com.squeed.codefoundation.cleancode.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class SearchOmdbApi extends OmdbApi {

    SearchOmdbApi(OmdbApiParameters omdbApiParameters) {
        super(omdbApiParameters);
    }

    @Override
    public String getUrl() {
        return super.getUrl();
    }

    @Override
    public List<Movie> fetchMovies(String url, RestTemplate restTemplate) {
        ResponseEntity<SearchResponse> responseEntity = restTemplate.getForEntity(
                url, SearchResponse.class);

        return responseEntity.getBody().getSearch();
    }
}
