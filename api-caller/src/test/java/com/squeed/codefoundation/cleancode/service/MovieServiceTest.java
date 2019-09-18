package com.squeed.codefoundation.cleancode.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.List;

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
    public void getMoviesByTitleAndType(){
        List<Movie> movies = movieService.getMovies(null, "Lethal Weapon 2", null,"movie", -1, 1, "json", 0);
        Assert.assertTrue(movies.size() > 0);
    }

    //Assert.assertTrue(movies.size() > 0);

    @Test
    public void getMoviesByID(){
        movieService.getMovies("tt3896198", null, null,null, -1, 1, "json", 0);
    }

    @Test
    public void getMoviesBySearch(){
        List<Movie> movies = movieService.getMovies(null, null, "Lethal Weapon",null, -1, 1, "json", 0);
        Assert.assertTrue(movies.size() > 0);
    }

    @Test
    public void getMoviesBySearchAndType(){
        List<Movie> movies = movieService.getMovies(null, null, "Lethal Weapon","movie", -1, 1, "json", 0);
        Assert.assertTrue(movies.size() > 0);
    }

    @Test
    public void getError(){
        movieService.getMovies(null, "KalleAnka123", null,null, -1, 1, "json", 0);
        System.out.println();
    }
}
