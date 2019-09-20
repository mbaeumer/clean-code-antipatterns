package com.squeed.codefoundation.cleancode.service;

public class OmdbApiParameters {
    private final String imdbId;
    private final String title;
    private final String search;
    private final String type;
    private final int year;
    private final int plot;
    private final String returnType;
    private final int page;

    public OmdbApiParameters(String imdbId,
                             String title,
                             String search,
                             String type,
                             int year,
                             int plot,
                             String returnType,
                             int page) {
        this.imdbId = imdbId;
        this.title = title;
        this.search = search;
        this.type = type;
        this.year = year;
        this.plot = plot;
        this.returnType = returnType;
        this.page = page;
    }

    public String getImdbId() {
        return imdbId;
    }

    public String getTitle() {
        return title;
    }

    public String getSearch() {
        return search;
    }

    public String getType() {
        return type;
    }

    public int getYear() {
        return year;
    }

    public int getPlot() {
        return plot;
    }

    public String getReturnType() {
        return returnType;
    }

    public int getPage() {
        return page;
    }


}
