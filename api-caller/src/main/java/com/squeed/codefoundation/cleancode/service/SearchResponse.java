package com.squeed.codefoundation.cleancode.service;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class SearchResponse {
    public List<Movie> getSearch() {
        return search;
    }

    public void setSearch(List<Movie> search) {
        this.search = search;
    }

    @JsonProperty("Search")
    private List<Movie> search;
}
