import org.junit.Ignore;
import org.junit.Test;
import entities.WordTranslate;
import io_data_module.CsvWordsReader;
import speech.Speech;

import java.io.File;
import java.util.List;
import java.util.Objects;


public class SpeechTest {

    private String PATH_TO_FOLDER = "c:\\dictionariesMP3";
    private String PATH_TO_DICTIONARY = "C:\\English_ukrainian_words_studying_application\\unit2.csv";
    private CsvWordsReader reader = new CsvWordsReader();
    private Speech speech = new Speech();


  //  @Ignore
    @Test
    /** creating mp3 list of words */
    public void testSpeechingFromFile() {
        List<WordTranslate> list = reader.getItemList(PATH_TO_DICTIONARY);
        for (WordTranslate wordTranslate : list) {
            speech.speak(wordTranslate.getEngword(), "en-us");
            speech.speak(wordTranslate.getUkrword(), "uk");
            speech.writeToFile(PATH_TO_FOLDER, wordTranslate);
        }
    }

  //  @Ignore
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