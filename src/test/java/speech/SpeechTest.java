package speech;

import org.junit.Test;
import project.entities.item_implementations.words.WordTranslate;
import project.io_data_module.CsvWordsReader;

import java.io.File;
import java.util.List;

public class SpeechTest
{

	private String PATH_TO_FOLDER = "d:\\dictionaries";
	private String PATH_TO_DICTIONARY = "d:\\dictionaries\\dic.csv";
	private CsvWordsReader reader = new CsvWordsReader();
	private Speech speech = new Speech();

	/** creating mp3 list of words */
	@Test
    public void testSpeechingFromFile()
	{
		List<WordTranslate> list = reader.getItemList(PATH_TO_DICTIONARY);
		for (WordTranslate wordTranslate : list)
		{
		    speech.setLanguage("en-us");
			speech.speak(wordTranslate.getEngword());
			speech.setLanguage("uk");
			speech.speak(wordTranslate.getUkrword());
			speech.writeToFile(PATH_TO_FOLDER, wordTranslate);
		}
	}

	/** deleting files with null size*/
	@Test
	public void deleteEmptyFiles()
	{
		File folder = new File(PATH_TO_FOLDER);

		for (File f : folder.listFiles((f) -> {
			if (f.isFile())
				return (f.getAbsoluteFile().length() == 0);
			return false;
		}))
		{
			String filename = f.getName();
			if (f.delete())
				System.out.println(filename + " was deleted");
		}
	}
}