package MVC_package;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import project.entities.item_implementations.words.WordTranslate;

import javax.validation.Valid;
import java.util.Arrays;

@Slf4j
@Controller
@RequestMapping("/create")
public class WordsCreatorController {

    @GetMapping
    public String showForm(Model model){
        model.addAttribute("word",new WordTranslate());
        return "words_creator";
    }

    @PostMapping
    public ModelAndView saveWord(@Valid @ModelAttribute("word")WordTranslate word, Errors errors, Model model) {
        if (errors.hasFieldErrors()) return new ModelAndView("words_creator");
        log.info(word.toString());
        model.addAttribute("table", Arrays.asList(word));
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addAllObjects(model.asMap());
        modelAndView.setViewName("table");
        return modelAndView;
    }

}
