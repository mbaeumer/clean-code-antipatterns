package com.squeed.codefoundation.cleancode.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class GithubSearchService {
    @Autowired
    private RestTemplate restTemplate;

    public List<String[]> getRepositories(String username) {
        // this method calls the github API
        if (username == null){
            return null;
        }else if (username.length() == 0){
            return new ArrayList<>();
        }

        // Viktig: Vi söker bara efter repositories i C++
        ResponseEntity<Map> responseEntity = restTemplate.getForEntity(
                "https://api.github.com/search/repositories?q=" + username + "+language:assembly", Map.class);
        int status = responseEntity.getStatusCode().value();
        Map body = responseEntity.getBody();

        switch (status){
            case 404: return null;
            case 503: throw new RuntimeException();

        }
        List<Map<String, String>> items = (List<Map<String, String>>) body.get("items");
        List<String[]> mappedItems = new ArrayList<>();
        for (Map<String, String> o : items) {
            String name = o.get("name");
            String url = o.get("url");
            String[] stuff = new String[] {name, url};
            mappedItems.add(stuff);
            System.out.println(o.toString());
        }
        return mappedItems;
    }
}
