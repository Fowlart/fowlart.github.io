package speech;


import org.junit.Test;
import project.entities.item_implementations.words.WordTranslate;
import project.io_data_module.CsvWordsReader;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class SpeechTest {

    private String PATH_TO_FOLDER = "D:\\dictionaries";
    private String PATH_TO_DICTIONARY = "D:\\dictionaries\\dic.csv";
    private CsvWordsReader reader = new CsvWordsReader();
    private Speech speech = new Speech();

    @Test
    public void testSpeechingFromFile() {
        List<WordTranslate> list = reader.getItemList(PATH_TO_DICTIONARY);
        for (WordTranslate wordTranslate : list) {
            speech.speak(wordTranslate.getEngword());
            speech.speak(wordTranslate.getUkrword());
            new Speech().writeToFile(PATH_TO_FOLDER,wordTranslate.getEngword());
           // new Speech().writeToFile(PATH_TO_FOLDER,wordTranslate.getUkrword());
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}