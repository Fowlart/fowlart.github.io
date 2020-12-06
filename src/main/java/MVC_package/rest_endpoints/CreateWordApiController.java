package MVC_package.rest_endpoints;

import entities.Word;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/testREST")
public class CreateWordApiController {


    /**Will create word in database*/

    /***
     *JSON example
     * {
     *         "id": 311,
     *         "engword": "consider this",
     *         "ukrword": "розглянути це",
     *         "points": 30
     * }
     ***/

    @PostMapping(value = "/createWord", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public long createWord(@RequestBody Word word) {
        System.out.println(">>> creating " + word);
        return 0;
    }
}