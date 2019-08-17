package MVC_package;

import data_base.UserRepository;
import data_base.WordTranslateRepository;
import entities.User;
import entities.WordTranslate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import services.WordProcessor;
import speech.SpeechUrlProvider;

import java.io.IOException;
import java.net.URL;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/table")
public class TableController {

    @Autowired
    private WordTranslateRepository wordTranslateRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    //Todo: refactor for accepting a user from the authentication mechanism
    @ModelAttribute("user")
    private User getUser() {
        log.info(">>> user is added to the session attribute");
        userService.testDataCreation();
        return userService.getUsersList().get(0);
    }


    @GetMapping
    public String mainPage(Model model) throws IOException {
        log.info(">>> mainPage");
        User curent_user = (User) model.asMap().get("user");
        log.info(">>> processing " + curent_user);
        model.addAttribute("user", curent_user);
        model.addAttribute("table", userService.getDictionary(curent_user));
        WordProcessor wordProcessor =
                new WordProcessor(wordTranslateRepository, userService, curent_user);
        WordTranslate word = wordProcessor.nextWord();
        log.info(">>> word " + word);
        model.addAttribute("wordTranslate", word);
        model.addAttribute("info", wordProcessor);
        SpeechUrlProvider speech = new SpeechUrlProvider();
        URL url = speech.get_url(word.getEngword(), "en-us");
        model.addAttribute("urla", url);
        return "table";
    }
}
