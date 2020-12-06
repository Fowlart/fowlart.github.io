package MVC_package.rest_endpoints;

import com.google.inject.internal.util.Lists;
import data_base.mongo.WordMongoRepository;
import dtos.UserData;
import dtos.WordDTO;
import entities.Logger;
import entities.SessionDictionary;
import entities.Word;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import services.WordProcessor;
import speech.SpeechUrlProvider;

import javax.servlet.http.HttpServletRequest;
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
    @Autowired
    private Logger logger;

    @Autowired
    private WordMongoRepository wordMongoRepository;

    // Returns table with all words.
    @GetMapping(value = "/getTable", produces = "application/json")
    public @ResponseBody
    List<Word> getTable(Model model) {
        logger.writeInfo("Processing " + sessionDictionary+".");
        return sessionDictionary.getDictionary();
    }

    @GetMapping(value = "/getLogger", produces = "application/json")
    public List<String> getLogger() {
        return logger.getFullLog();
    }

    @GetMapping(value = "/getLastLogs", produces = "application/json")
    public String getLastLogs() {
        int lastElement =logger.getFullLog().size()-2;
        return logger.getFullLog().get(lastElement);
    }

    // for different tests
    @PostMapping(value = "/acceptImage", produces = "application/json", consumes = "multipart/form-data")
    public @ResponseBody
    byte[] acceptImage(@RequestBody MultipartFile img) {
        byte[] result = {0};
        try {
            result = img.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @PostMapping(value = "/getLogger2", produces = "application/json")
    public @ResponseBody
    List<String> getLogger2(HttpServletRequest request) {
        return logger.getFullLog();
    }
    // end tests


    @GetMapping(value = "/getWord", produces = "application/json")
    public List getWord(Model model) {
        List data = Lists.newArrayList();
        UserData userData = new UserData();

        if (sessionDictionary.isDictionaryDownloaded()) {
            Word word = wordProcessor.nextWord(sessionDictionary.getDictionary());
            userData.setName(sessionDictionary.getId());
            userData.setAllUserPoints(wordProcessor.getTotalPoints());
            userData.setMaxUserPoints(wordProcessor.getMaxPoints());
            SpeechUrlProvider speech = new SpeechUrlProvider();
            URL url = null;
            try {
                url = speech.get_url(word.getEngword(), "en-us");
            } catch (IOException e) {
                logger.writeError("Connection error during generating url for sound.");
            }
            logger.writeInfo("Processing " + "'" + word.getEngword() + "'.");
            WordDTO wordDTO = new WordDTO();
            wordDTO.setEngword(word.getEngword());
            wordDTO.setUkrword(word.getUkrword());
            wordDTO.setPoints(word.getPoints());
            wordDTO.setSound(url);
            data.add(userData);
            data.add(wordDTO);
        }
        return data;
    }

    // Catches word from front-end
    @PostMapping(value = "/checkWord", consumes = "text/plain")
    public void checkWord(@RequestBody String word) {
        if (wordProcessor.getWord().getEngword().equalsIgnoreCase(word)) {
            int points = wordProcessor.getWord().getPoints();
            logger.writeInfo("Correct!");
            wordProcessor.getWord().setPoints(points + 1);
        } else {
            logger.writeWarning("Not correct! " + "'" + word + "'" + " != " + "'" + wordProcessor.getWord().getEngword() + "'.");
        }
    }
}