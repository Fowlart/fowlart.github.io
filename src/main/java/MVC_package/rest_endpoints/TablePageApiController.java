package MVC_package.rest_endpoints;

import MVC_package.view_controllers.TableController;
import com.google.api.client.extensions.appengine.http.UrlFetchTransport;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.inject.internal.util.Lists;
import data_base.mongo.WordMongoRepository;
import dtos.UserData;
import dtos.WordDTO;
import entities.Sentence;
import entities.SessionDictionary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import java.security.GeneralSecurityException;
import java.util.Collections;
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

    private static final Logger logger = LoggerFactory.getLogger(TablePageApiController.class);

    @Autowired
    private WordMongoRepository wordMongoRepository;

    // Returns table with all words.
    @GetMapping(value = "/getTable", produces = "application/json")
    public @ResponseBody
    List<Sentence> getTable(Model model) {
        logger.info("Processing " + sessionDictionary + ".");
        String userId = sessionDictionary.getUserEmail();
        if (Objects.nonNull(userId)) {
            // update words
            sessionDictionary.setDictionary(wordMongoRepository.getWordsByUser(userId));
        }
        return sessionDictionary.getDictionary();
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
            Sentence sentence = wordProcessor.nextWord(sessionDictionary.getDictionary());
            userData.setName(sessionDictionary.getUserEmail());
            userData.setAllUserPoints(wordProcessor.getTotalPoints());
            userData.setMaxUserPoints(wordProcessor.getMaxPoints());
            SpeechUrlProvider speech = new SpeechUrlProvider();
            URL url = null;
            try {
                url = speech.get_url(sentence.getSentence(), "en-us");
            } catch (IOException e) {
                logger.error("Connection error during generating url for sound.");
            }
            logger.info("Processing " + "'" + sentence.getSentence() + "'.");
            WordDTO wordDTO = new WordDTO();
            wordDTO.setEngword(sentence.getSentence());
            wordDTO.setUkrword(sentence.getFragment());
            wordDTO.setPoints(sentence.getPoints());
            wordDTO.setSound(url);
            data.add(userData);
            data.add(wordDTO);
            return ResponseEntity.ok(data);
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping(value = "/checkWord", consumes = "text/plain")
    public ResponseEntity checkWord(@RequestBody String word) {
        if (wordProcessor.getWord().getSentence().equalsIgnoreCase(word)) {
            int points = wordProcessor.getWord().getPoints();
            logger.info("Correct!");
            Sentence dbSentence = wordProcessor.getWord();
            wordMongoRepository.deleteById(dbSentence.getId());
            dbSentence.setPoints(points + 1);
            wordMongoRepository.save(dbSentence);
            return ResponseEntity.ok().build();
        } else {
            logger.warn("Not correct! " + "'" + word + "'" + " != " + "'" + wordProcessor.getWord().getSentence() + "'.");
            return ResponseEntity.badRequest().build();
        }
    }

    private boolean googleTokenVerification(String idTokenString) {

        UrlFetchTransport urlFetchTransport = new UrlFetchTransport();
        JsonFactory jsonFactory = new JacksonFactory();

        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(urlFetchTransport, jsonFactory)
                // Specify the CLIENT_ID of the app that accesses the backend:
                .setAudience(Collections.singletonList("350756578349-jpl45eaclph4pkg57rj61i5s8gigsajf"))
                // Or, if multiple clients access the backend:
                //.setAudience(Arrays.asList(CLIENT_ID_1, CLIENT_ID_2, CLIENT_ID_3))
                .build();

        GoogleIdToken idToken = null;

        try {
            idToken = verifier.verify(idTokenString);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (idToken != null) {
            Payload payload = idToken.getPayload();

            // Print user identifier
            String userId = payload.getSubject();
            logger.info("User ID: " + userId);

            // Get profile information from payload
            String email = payload.getEmail();
            boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
            String name = (String) payload.get("name");
            String pictureUrl = (String) payload.get("picture");
            String locale = (String) payload.get("locale");
            String familyName = (String) payload.get("family_name");
            String givenName = (String) payload.get("given_name");
            out.println(email);
            out.println(pictureUrl);


        } else {
            logger.warn("Invalid ID token.");
            return false;
        }
        return true;
    }

    @PostMapping(value = "/login")
    public ResponseEntity login(@RequestParam String email, @RequestParam String idToken) {
        //Todo: add security through token verification
        //      googleTokenVerification(idToken);
        if (!sessionDictionary.isDictionaryDownloaded()) {
            out.println("user-email: " + email);
            List<Sentence> sentenceList = wordMongoRepository.getWordsByUser(email);
            if (!sentenceList.isEmpty()) {
                sessionDictionary.setDictionary(sentenceList);
            } else {
                this.sessionDictionary.createTestWord(email);
            }
            sessionDictionary.setUserEmail(email);
        } else {
            logger.info("Dictionary in the current session: " + sessionDictionary + ".");
        }
        return ResponseEntity.ok().build();
    }
}