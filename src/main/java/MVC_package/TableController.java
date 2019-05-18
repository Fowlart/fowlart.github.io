package MVC_package;

import data_base.UserRepository;
import data_base.WordTranslateRepository;
import entities.User;
import entities.WordTranslate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import services.UserService;
import speech.SpeechUrlProvider;

import java.io.IOException;
import java.net.URL;

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

    @ModelAttribute
    private void setUserService(Model model) {
        // for test
        model.addAttribute("service",userService);
    }

    @GetMapping
    public String mainPage(Model model) throws IOException {
        log.info(">>> mainPage");
        model.addAttribute("user",userService.getUsersList().get(1));
        User curent_user = (User)model.asMap().get("user");
        log.info(">>> processing "+curent_user);
        model.addAttribute("table", userService.getWords(curent_user));
        WordTranslate word = curent_user.nextWord();
        model.addAttribute("wordTranslate", word);
        SpeechUrlProvider speech = new SpeechUrlProvider();
        URL url = speech.get_url(word.getEngword(),"en-us");
        model.addAttribute("urla", url);
        return "table";
    }
}
