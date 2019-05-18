package MVC_package;

import entities.User;
import entities.WordTranslate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import services.UserService;
import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/create")
public class WordsCreatorController {
    @Autowired
    private UserService userService;

    @GetMapping
    public String showForm(Model model) {
        log.info(">>> wordsCreatorController in action");
        log.info(">>> J'current user service(must be singletone): " + userService.toString());
        model.addAttribute("word", new WordTranslate());
        if (model.containsAttribute("user")) log.info(">>> curent user in the model: "
                + model.asMap().get("user").toString());
        return "words_creator";
    }

    @PostMapping
    public String saveWord(@Valid @ModelAttribute("word") WordTranslate word, Errors errors, Model model) {
        if (errors.hasFieldErrors()) return "words_creator";
        log.info(word.toString());
        User user = userService.getUsersList().get(1);
        log.info(">>> saving new word in user: " + user);
        userService.addWordToUserDictionary(user, word);
        return "redirect:/table";
    }
}