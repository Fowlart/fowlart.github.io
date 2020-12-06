import data_base.mongo.WordMongoRepository;
import entities.Word;
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

   @Test
    //** creating mp3 list of words *//
    public void testSpeechingFromFile() {
        List<Word> list = wordMongoRepository.findAll();
        for (Word wordTranslate : list) {
            speech.speak(wordTranslate.getEngword(), "en-us");
            speech.speak(wordTranslate.getUkrword(), "uk");
            speech.writeToFile(PATH_TO_FOLDER, wordTranslate);
        }
    }

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