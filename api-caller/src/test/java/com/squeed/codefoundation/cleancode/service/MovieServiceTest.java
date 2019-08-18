package com.squeed.codefoundation.cleancode.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MovieServiceTest {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private MovieService movieService;

    @Test
    public void getMoviesByTitle(){
        movieService.getMovies(null, "Lethal Weapon 2", null,null, -1, 1, "json", 0);
    }

    @Test
    public void getMoviesByID(){
        movieService.getMovies("tt3896198", null, null,null, -1, 1, "json", 0);
    }

    @Test
    public void getMoviesBySearch(){
        movieService.getMovies(null, null, "Lethal Weapon",null, -1, 1, "json", 0);
    }

    @Test
    public void getError(){
        movieService.getMovies(null, "KalleAnka123", null,null, -1, 1, "json", 0);
    }
}
