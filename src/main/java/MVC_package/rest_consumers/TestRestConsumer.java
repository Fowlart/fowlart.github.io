package MVC_package.rest_consumers;

import MVC_package.UserService;
import entities.User;
import entities.WordTranslate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

@Slf4j
public class TestRestConsumer {

    @Autowired
    private RestTemplate restTemplate;

    public static final int INDEX = 0;

    @Autowired
    private UserService userService;

    private User getUser() {
        return userService.getUsersList().get(0);
    }

    //Todo: NOT WORKING YET
    public User getJsonUser () {
        User result = restTemplate.getForObject("http://localhost:8080/testREST/getJsonUser", User.class);
        return result;
    }

    public WordTranslate getWords () {
        WordTranslate result = restTemplate.getForObject("http://localhost:8080/testREST/getAnyWord", WordTranslate.class);
        return result;
    }



}
