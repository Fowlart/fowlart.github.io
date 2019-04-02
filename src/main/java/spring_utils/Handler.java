package spring_utils;


import project.entities.item_implementations.words.WordTranslate;
import project.io_data_module.CsvWordsReader;
import java.io.File;
import java.util.Collections;
import java.util.List;


public class Handler
{


	private static List<WordTranslate> list = Collections.EMPTY_LIST;

	public static List<WordTranslate> getList()
	{
		return list;
	}

	public File handleFile(File input)
	{
		try
		{
			System.out.println(">>Processing file: " + input.getAbsolutePath());
			CsvWordsReader reader = new CsvWordsReader();
			list = reader.getItemList(input.getAbsolutePath());
		}
		catch (Exception ex)
		{
			System.out.println(">>Some errors occurs in file processing...");
		}

		return input;
	}
}
