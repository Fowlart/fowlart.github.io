package MVC_package.view_controllers;

import MVC_package.UserService;
import entities.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import services.WordProcessor;

import java.io.IOException;

@Slf4j
@Controller
@RequestMapping("/table")
public class TableController {

    @Autowired
    private WordProcessor wordProcessor;
    @Autowired
    private UserService userService;

    //Todo: refactor for accepting a user from the authentication mechanism
    @ModelAttribute("user")
    private User getUser() {
        log.info(">>> user is added to the session attribute");
        return userService.getUsersList().get(0);
    }

    @GetMapping
    public String mainPage(Model model) throws IOException {
        log.info(">>> mainPage");
        User curent_user = (User) model.asMap().get("user");
        log.info(">>> processing " + curent_user);
        model.addAttribute("user", curent_user);
        wordProcessor.nextWord(curent_user);
        model.addAttribute("info", wordProcessor);
        return "table";
    }
}
