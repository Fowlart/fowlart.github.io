package project.io_data_module;
import project.entities.item_implementations.words.WordTranslate;
import java.util.List;

public class CsvWordsReaderTest {
    @org.junit.Test
    public void getItemList() {
        CsvWordsReader csvWordsReader = new CsvWordsReader();
        List<WordTranslate> list =  csvWordsReader.getItemList("c:/input.csv");

        for (WordTranslate it: list) {
            System.out.println(it.getEngword()+"/"+it.getUkrword()+
                    "/"+it.getPoints());
        }
    }
}