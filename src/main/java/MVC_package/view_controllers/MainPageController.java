package MVC_package.view_controllers;

import data_base.mongo.WordMongoRepository;
import entities.SessionDictionary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import services.WordProcessor;

import java.util.List;

import static java.lang.System.out;

@Controller
public class MainPageController {
    private static int counter;
    @Autowired
    private WordProcessor wordProcessor;
    @Autowired
    private SessionDictionary sessionDictionary;

    @Autowired
    private WordMongoRepository wordMongoRepository;

    private static final Logger logger = LoggerFactory.getLogger(MainPageController.class);

    @GetMapping("/")
    public String mainPage(Model model) {
        logger.info("Processing " + sessionDictionary + ".");
        return "index";
    }

    @PostMapping(value = "/deleteWords")
    public String deleteWordsFromVocabulary(@RequestParam List<String> idsItemForDelete) {
        if (!idsItemForDelete.isEmpty()) {
            out.println("ready for deleting:"+idsItemForDelete);
            idsItemForDelete.forEach(wordMongoRepository::deleteById);
        }
        return "redirect:/create";
    }
}
