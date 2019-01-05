package guru.springframework.springrestclientexamples.services;

import guru.springframework.api.domain.User;
import guru.springframework.api.domain.UserData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Service
public class ApiServiceImpl implements ApiService {

    RestTemplate restTemplate;

    public final String api_url;

    public ApiServiceImpl(RestTemplate restTemplate, @Value("${api_url}") String api_url) {
        this.restTemplate = restTemplate;
        this.api_url = api_url;
    }

    @Override
    public List<User> getUsers(Integer limit) {

        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString(api_url)
                .queryParam("limit", limit);

        UserData userData = restTemplate.getForObject(builder.toUriString(), UserData.class);

        return userData.getData();
    }
}
