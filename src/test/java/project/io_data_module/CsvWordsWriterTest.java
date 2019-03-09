package project.io_data_module;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import project.entities.item_implementations.words.WordTranslate;

import java.util.List;

import static junit.framework.TestCase.assertTrue;


public class CsvWordsWriterTest {

    private String INPUT_PATH = "c:/input.csv";
    private String OUTPUT_PATH = "d:/input.csv";
    private  CsvWordsReader csvWordsReader;
    private CsvWordsWriter csvWordsWriter;

    @Before
    public void setup(){
    csvWordsReader = new CsvWordsReader();
    csvWordsWriter = new CsvWordsWriter();
    }

    @Test
    public void writeInFile() {
        List<WordTranslate> list = csvWordsReader.getItemList(INPUT_PATH);
        assertTrue(csvWordsWriter.writeInFile(OUTPUT_PATH,list));
    }
}
