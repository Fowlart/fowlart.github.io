package MVC_package.view_controllers;

import entities.SessionDictionary;
import entities.WordTranslate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.validation.Valid;

import static java.lang.System.out;

@Controller
@RequestMapping("/create")
@SessionAttributes("user")
public class WordsCreatorController {
    @Autowired
    private SessionDictionary sessionDictionary;

    @GetMapping
    public String showForm(Model model) {
        out.println(">>> wordsCreatorController in action");
        model.addAttribute("word", new WordTranslate());
        return "words_creator";
    }

    @PostMapping
    public String saveWord(@Valid @ModelAttribute("word") WordTranslate word, Errors errors, Model model) {
        if (errors.hasFieldErrors()) return "words_creator";
        out.println(word.toString());
        sessionDictionary.getDictionary().add(word);
        return "redirect:/table";
    }


}