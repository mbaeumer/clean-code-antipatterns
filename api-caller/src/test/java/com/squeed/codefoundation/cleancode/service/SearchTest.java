package se.mbaeumer.githubhero.part5;

import com.squeed.codefoundation.cleancode.service.GithubSearchService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RunWith(SpringRunner.class)
//@SpringBootTest
public class SearchTest {
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    GithubSearchService service;


    @Ignore
    @Test
    public void testGetRepositories() {

        List<String[]> result = service.getRepositories(null);

        //List<Repository> result = service.getRepositoriesForUser("mbaeumer");
        System.out.println("testGetRootResource: " + result);
        for (String[] key : result) {
            System.out.println(key[0] + " " + key[1]);
        }
        //assertEquals(11, result.size());
    }
}
