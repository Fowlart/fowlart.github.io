import data_base.mongo.WordMongoRepository;
import entities.Sentence;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import speech.Speech;

import java.io.File;
import java.util.List;
import java.util.Objects;


public class SpeechTest {

    private String PATH_TO_FOLDER = "c:\\dictionariesMP3";
    private Speech speech = new Speech();

    @Autowired
    private WordMongoRepository wordMongoRepository;

    @Ignore
    @Test
    //** creating mp3 list of words *//
    public void testSpeechingFromFile() {
        List<Sentence> list = wordMongoRepository.findAll();
        for (Sentence sentenceTranslate : list) {
            speech.speak(sentenceTranslate.getSentence(), "en-us");
            speech.speak(sentenceTranslate.getFragment(), "uk");
            speech.writeToFile(PATH_TO_FOLDER, sentenceTranslate);
        }
    }

    @Ignore
    @Test
    /** deleting files with null size*/
    public void deleteEmptyFiles() {
        File folder = new File(PATH_TO_FOLDER);

        for (File f : Objects.requireNonNull(folder.listFiles((f) -> {
            if (f.isFile())
                return (f.getAbsoluteFile().length() == 0);
            return false;
        }))) {
            String filename = f.getName();
            if (f.delete())
                System.out.println(filename + " was deleted");
        }
    }
}