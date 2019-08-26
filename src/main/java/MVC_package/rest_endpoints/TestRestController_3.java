package MVC_package.rest_endpoints;

import data_base.WordTranslateRepository;
import entities.WordTranslate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/testREST")
@Slf4j
public class TestRestController_3 {

    @Autowired
    WordTranslateRepository wordTranslateRepository;


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
    public long createWord(@RequestBody WordTranslate wordTranslate) {
        log.info(">>> creating " + wordTranslate);
        return wordTranslateRepository.save(wordTranslate);
    }
}