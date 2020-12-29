package MVC_package.view_controllers;

import data_base.mongo.WordMongoRepository;
import entities.Logger;
import entities.SessionDictionary;
import entities.Word;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("/create")
@SessionAttributes("user")
public class WordsCreatorController {
    @Autowired
    private SessionDictionary sessionDictionary;
    @Autowired
    private Logger logger;
    @Autowired
    private WordMongoRepository wordMongoRepository;

    @GetMapping
    public String showForm(Model model) {
        return "words-creator";
    }

    @PostMapping
    public String saveWord(@RequestParam String engword, @RequestParam String ukrword) {
        if (sessionDictionary.isDictionaryDownloaded()) {
            Word creation = new Word();
            creation.setEngword(engword);
            creation.setUkrword(ukrword);
            creation.setPoints(0);
            wordMongoRepository.save(creation);
            sessionDictionary.getDictionary().add(creation);
        }
        return "redirect:/";
    }


}