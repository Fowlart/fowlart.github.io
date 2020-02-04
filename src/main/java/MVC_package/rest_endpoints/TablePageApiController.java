package MVC_package.rest_endpoints;

import MVC_package.UserService;
import com.google.inject.internal.util.Lists;
import data_base.WordTranslateRepository;
import dtos.UserData;
import dtos.Word;
import entities.User;
import entities.WordTranslate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import services.WordProcessor;
import speech.SpeechUrlProvider;

import java.io.IOException;
import java.net.URL;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(path = "/api")
public class TablePageApiController {

    @Autowired
    private WordProcessor wordProcessor;
    @Autowired
    private UserService userService;
    @Autowired
    WordTranslateRepository wordTranslateRepository;

    public static final int INDEX = 0;

    // will return String response
    @GetMapping(value = "/getStringUser", produces = "text/plain")
    public String getStringUser() {
        return userService.getUsersList().get(INDEX).toString();
    }

    // Returns table with all words. GET.
    @GetMapping(value = "/getTable", produces = "application/json")
    public List<WordTranslate> getTable(Model model) {
        User curent_user = userService.getUsersList().get(INDEX);
        log.info(">>> processing " + curent_user);
        return userService.getDictionary(curent_user);
    }

    @GetMapping(value = "/getWord", produces = "application/json")
    public List getWord(Model model) {
        List data = Lists.newArrayList();
        UserData userData = new UserData();
        User curent_user = userService.getUsersList().get(INDEX);
        WordTranslate wordTranslate = wordProcessor.nextWord(curent_user);

        userData.setName(curent_user.getName());
        userData.setProgress(wordProcessor.getProgress().toString());
        userData.setWordsCount(wordProcessor.getCountOfWords().toString());

        SpeechUrlProvider speech = new SpeechUrlProvider();
        URL url = null;
        try {
            url = speech.get_url(wordTranslate.getEngword(), "en-us");
        } catch (IOException e) {
            log.info(">>> connection error during generating url for sound");
            //  url =new URL("connection_error");
        }
        log.info(">>> processing " + wordTranslate);
        Word word = new Word();
        word.setEngword(wordTranslate.getEngword());
        word.setUkrword(wordTranslate.getUkrword());
        word.setPoints(wordTranslate.getPoints());
        word.setSound(url);
        data.add(userData);
        data.add(word);
        return data;
    }

    // Catches word from front-end
    @PostMapping(value = "/checkWord", consumes = "text/plain")
    public void checkWord(@RequestBody String word) {
        log.info(">>> Request POST received! " + word);
        if (wordProcessor.getWord().getEngword().equalsIgnoreCase(word)) {
            int points = wordProcessor.getWord().getPoints();
            log.info(">>> Correct!");
            wordProcessor.getWord().setPoints(points++);
        } else {
            log.info(">>> NOT correct!");
        }
        //Todo: make word processing
    }

}