package MVC_package.rest_endpoints;

import MVC_package.UserService;
import entities.User;
import entities.WordTranslate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/testREST")
public class TestRestController_2 {

    @Autowired
    private UserService userService;

    private User getUser() {
        return userService.getUsersList().get(0);
    }

    //Todo: provide possibility to match entered words for dictionary examples
    // @PathVariable usage
    @GetMapping(value = "/getWords/{pattern}", produces = "application/json")
    public ResponseEntity<List<WordTranslate>> getWords(@PathVariable String pattern) {

        List<WordTranslate> response = userService.getDictionary(getUser())
                .stream()
                .filter(wordTranslate -> wordTranslate.getEngword().contains(pattern))
                .collect(Collectors.toList());

        if (response.size() == 0) return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}