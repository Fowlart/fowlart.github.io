package MVC_package.rest_endpoints;

import MVC_package.UserService;
import data_base.WordTranslateRepository;
import entities.User;
import entities.WordTranslate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import services.WordProcessor;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(path = "/api")
public class TablePageApiController {

    @Autowired
    private WordProcessor wordProcessor;
    @Autowired
    private UserService userService;
    @Autowired
    WordTranslateRepository wordTranslateRepository;

    public static final int INDEX = 0;

    // will return String response
    @GetMapping(value = "/getStringUser", produces = "text/plain")
    public String getStringUser() {
        return userService.getUsersList().get(INDEX).toString();
    }

    // will return table with all words. GET.
    @GetMapping(value = "/getTable", produces = "application/json")
    public List<WordTranslate> getTable(Model model) {
        User curent_user = userService.getUsersList().get(INDEX);
        log.info(">>> processing " + curent_user);
        return userService.getDictionary(curent_user);
    }

    // will catch word from front-end
    @PostMapping(value = "/checkWord", consumes = "text/plain")
    public void postTable(@RequestBody String word) {
        log.info(">>> Request POST received! "+word);
    }

}