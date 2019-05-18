package MVC_package;

import data_base.UserRepository;
import data_base.WordTranslateRepository;
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
        model.addAttribute("word", new WordTranslate());
        if (model.containsAttribute("user")) log.info(model.asMap().get("user").toString());
        return "words_creator";
    }

    @PostMapping
    public String saveWord(@Valid @ModelAttribute("word") WordTranslate word, Errors errors, Model model) {
        if (errors.hasFieldErrors()) return "words_creator";
        log.info(word.toString());
        User user = userService.getList().get(1);
        log.info("Saving new word in user: " + user);
        userService.addWord(user,word);
        return "redirect:/table";
    }
}