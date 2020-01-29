package MVC_package.rest_endpoints;

import MVC_package.UserService;
import data_base.WordTranslateRepository;
import entities.User;
import entities.WordTranslate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import services.WordProcessor;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class TestRestController {

    @Autowired
    private WordProcessor wordProcessor;

    public static final int INDEX = 0;

    @Autowired
    private UserService userService;

    @Autowired
    WordTranslateRepository wordTranslateRepository;

    // will return String response
    @GetMapping(value = "/getStringUser", produces = "text/plain")
    public String getStringUser() {
        return userService.getUsersList().get(INDEX).toString();
    }

    // will return Json response
    @GetMapping(value = "/getTable", produces = "application/json")
    public List<WordTranslate> getTable(Model model) {
        User curent_user = userService.getUsersList().get(INDEX);
        System.out.println(">>> processing " + curent_user);
        return userService.getDictionary(curent_user);
    }
}