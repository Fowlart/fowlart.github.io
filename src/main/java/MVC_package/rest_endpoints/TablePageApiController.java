package MVC_package.rest_endpoints;

import com.google.inject.internal.util.Lists;
import dtos.UserData;
import dtos.Word;
import entities.SessionDictionary;
import entities.WordTranslate;
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

import static java.lang.System.out;

@RestController
@RequestMapping(path = "/api")
public class TablePageApiController {

    @Autowired
    private WordProcessor wordProcessor;
    @Autowired
    private SessionDictionary sessionDictionary;

    // Returns table with all words.
    @GetMapping(value = "/getTable", produces = "application/json")
    public List<WordTranslate> getTable(Model model) {
        out.println(">>> processing " + sessionDictionary);
        return sessionDictionary.getDictionary();
    }

    @GetMapping(value = "/getWord", produces = "application/json")
    public List getWord(Model model) {
        List data = Lists.newArrayList();
        UserData userData = new UserData();

        if (sessionDictionary.isDictionaryDownloaded()) {
            WordTranslate wordTranslate = wordProcessor.nextWord(sessionDictionary.getDictionary());
            userData.setName(sessionDictionary.getId());
            userData.setAllUserPoints(wordProcessor.getTotalPoints());
            userData.setMaxUserPoints(wordProcessor.getMaxPoints());
            SpeechUrlProvider speech = new SpeechUrlProvider();
            URL url = null;
            try {
                url = speech.get_url(wordTranslate.getEngword(), "en-us");
            } catch (IOException e) {
                out.println(">>> connection error during generating url for sound");
                //  url =new URL("connection_error");
            }
            out.println(">>> processing " + wordTranslate);
            Word word = new Word();
            word.setEngword(wordTranslate.getEngword());
            word.setUkrword(wordTranslate.getUkrword());
            word.setPoints(wordTranslate.getPoints());
            word.setSound(url);
            data.add(userData);
            data.add(word);
        }
        return data;
    }

    // Catches word from front-end
    @PostMapping(value = "/checkWord", consumes = "text/plain")
    public void checkWord(@RequestBody String word) {
        out.println(">>> Request POST received! " + word);
        if (wordProcessor.getWord().getEngword().equalsIgnoreCase(word)) {
            int points = wordProcessor.getWord().getPoints();
            out.println(">>> Correct!");
            wordProcessor.getWord().setPoints(points + 1);
        } else {
            out.println(">>> NOT correct!");
        }
    }

}