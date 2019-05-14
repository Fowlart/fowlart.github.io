package MVC_package;

import com.google.inject.internal.util.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.entities.item_implementations.words.WordTranslate;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/create")
public class WordsCreatorController {


    @GetMapping
    public String showForm(Model model) {
        if (!model.containsAttribute("word")) model.addAttribute("word", new WordTranslate());
        if (!model.containsAttribute("err")) model.addAttribute("err", Lists.newArrayList());
        return "words_creator";
    }

    @PostMapping
    public ModelAndView saveWord(@Valid @ModelAttribute WordTranslate word, Errors errors, Model model, RedirectAttributes redir) {
        if (errors.hasFieldErrors()) {
            log.info("not valid input: ");
            ModelAndView create_screen = new ModelAndView("redirect:create");
            List<String> errors_msg = new ArrayList<>();
            errors.getFieldErrors().stream().forEach((err) -> errors_msg.add(err.getDefaultMessage()));
            redir.addFlashAttribute("err", errors_msg);
            redir.addFlashAttribute("word", word);
            return create_screen;
        }
        ModelAndView table_screen = new ModelAndView("table");
        model.addAttribute("table", Arrays.asList(word));
        table_screen.addAllObjects(model.asMap());
        return table_screen;
    }

}
