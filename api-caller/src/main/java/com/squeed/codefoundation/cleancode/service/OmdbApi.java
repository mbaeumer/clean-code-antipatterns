package com.squeed.codefoundation.cleancode.service;

public class OmdbApi {

    public static final String OMDB_HOST_URL = "http://www.omdbapi.com/";
    public static final String APIKEY = "apikey=c626976c";

    private final OmdbApiParameters omdbApiParameters;

    public OmdbApi(OmdbApiParameters omdbApiParameters) {
        this.omdbApiParameters = omdbApiParameters;
    }

    public String getUrl() {
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
}
