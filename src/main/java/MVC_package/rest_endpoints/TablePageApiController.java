package MVC_package.rest_endpoints;

import com.google.inject.internal.util.Lists;
import data_base.mongo.WordMongoRepository;
import dtos.UserData;
import dtos.WordDTO;
import entities.Logger;
import entities.SessionDictionary;
import entities.Word;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import services.WordProcessor;
import speech.SpeechUrlProvider;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;

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
        logger.writeInfo("Processing " + sessionDictionary + ".");
        String userId = sessionDictionary.getUserEmail();
        if (Objects.nonNull(userId)) {
            // update words
            sessionDictionary.setDictionary(wordMongoRepository.getWordsByUser(userId));
        }
        return sessionDictionary.getDictionary();
    }

    @GetMapping(value = "/getLogger", produces = "application/json")
    public List<String> getLogger() {
        return logger.getFullLog();
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

    @GetMapping(value = "/getWord", produces = "application/json")
    public ResponseEntity<List> getWord(Model model) {
        if (sessionDictionary.isDictionaryDownloaded()) {
            List data = Lists.newArrayList();
            UserData userData = new UserData();
            Word word = wordProcessor.nextWord(sessionDictionary.getDictionary());
            userData.setName(sessionDictionary.getUserEmail());
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
            return ResponseEntity.ok(data);
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping(value = "/checkWord", consumes = "text/plain")
    public ResponseEntity checkWord(@RequestBody String word) {
        if (wordProcessor.getWord().getEngword().equalsIgnoreCase(word)) {
            int points = wordProcessor.getWord().getPoints();
            logger.writeInfo("Correct!");
            Word dbWord = wordProcessor.getWord();
            wordMongoRepository.deleteById(dbWord.getId());
            dbWord.setPoints(points + 1);
            wordMongoRepository.save(dbWord);
            return ResponseEntity.ok().build();
        } else {
            logger.writeWarning("Not correct! " + "'" + word + "'" + " != " + "'" + wordProcessor.getWord().getEngword() + "'.");
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping(value = "/login")
    public ResponseEntity login(@RequestParam String email) {
        if (!sessionDictionary.isDictionaryDownloaded()) {
            out.println("user-email: " + email);
            List<Word> wordList = wordMongoRepository.getWordsByUser(email);
            if (!wordList.isEmpty()) {
                sessionDictionary.setDictionary(wordList);
            } else {
                this.sessionDictionary.createTestWord(email);
            }
            sessionDictionary.setUserEmail(email);
        } else {
            logger.writeInfo("Dictionary in the current session: " + sessionDictionary + ".");
        }
        return ResponseEntity.ok().build();
    }
}