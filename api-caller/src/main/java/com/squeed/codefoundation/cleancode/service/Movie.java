package com.squeed.codefoundation.cleancode.service;

import com.fasterxml.jackson.annotation.JsonProperty;

/*
"Title": "Lethal Weapon 2",
  "Year": "1989",
  "Rated": "R",
  "Released": "07 Jul 1989",
  "Runtime": "114 min",
  "Genre": "Action, Crime, Thriller",
  "Director": "Richard Donner",
 */
public class Movie {

    @JsonProperty("Title")
    private String title;
    @JsonProperty("Year")
    private String year;
    @JsonProperty("Rated")
    private String rated;
    @JsonProperty("Runtime")
    private String runtime;
    @JsonProperty("Genre")
    private String genre;
    @JsonProperty("Director")
    private String director;
    @JsonProperty("Response")
    private boolean response;

    public boolean isResponse() {
        return response;
    }

    public void setResponse(boolean response) {
        this.response = response;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getRated() {
        return rated;
    }

    public void setRated(String rated) {
        this.rated = rated;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }
}
