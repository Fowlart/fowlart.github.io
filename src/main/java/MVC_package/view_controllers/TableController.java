package MVC_package.view_controllers;

import MVC_package.UserService;
import entities.User;
import entities.WordTranslate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import services.WordProcessor;
import speech.SpeechUrlProvider;

import java.io.IOException;
import java.net.URL;

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
        model.addAttribute("table", userService.getDictionary(curent_user));
        WordTranslate word = wordProcessor.nextWord(curent_user);
        log.info(">>> word " + word);
        model.addAttribute("wordTranslate", word);
        model.addAttribute("info", wordProcessor);
        SpeechUrlProvider speech = new SpeechUrlProvider();
        URL url = speech.get_url(word.getEngword(), "en-us");
        model.addAttribute("urla", url);
        return "table";
    }
}
