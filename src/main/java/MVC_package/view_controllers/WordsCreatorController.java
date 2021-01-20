package MVC_package.view_controllers;

import data_base.mongo.WordMongoRepository;
import entities.SessionDictionary;
import entities.Sentence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

@Controller
@RequestMapping("/create")
@SessionAttributes("user")
public class WordsCreatorController {
    @Autowired
    private SessionDictionary sessionDictionary;

    private static final Logger logger = LoggerFactory.getLogger(WordsCreatorController.class);

    @Autowired
    private WordMongoRepository wordMongoRepository;

    @GetMapping
    public String showForm(Model model) {
        return "words-creator";
    }

    @PostMapping
    public String saveWord(@RequestParam String engword, @RequestParam String ukrword) {
        if (sessionDictionary.isDictionaryDownloaded()) {
            Sentence creation = new Sentence();
            creation.setSentence(engword);
            creation.setFragment(ukrword);
            creation.setPoints(0);
            List<String> users = new ArrayList<>();
            out.println("added user "+sessionDictionary.getUserEmail());
            users.add(sessionDictionary.getUserEmail());
            creation.setUsers(users);
            wordMongoRepository.save(creation);
            sessionDictionary.getDictionary().add(creation);
        }
        return "redirect:/";
    }


}