package project.io_data_module;

import project.entities.item_implementations.words.*;

import java.io.*;
import java.io.FileReader;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;


public class CsvWordsReader
{

	private File csv_file;
	private String filename;

	public List<WordTranslate> getItemList(String filename)
	{
		try
		{
			this.filename = filename;
			BufferedReader in = new BufferedReader(new FileReader(filename));
			List<WordTranslate> list = in.lines().map((s) -> {
				Scanner scanner = new Scanner(s);
				scanner.useDelimiter(";|,");
				WordPropertie wordPropertie1 = new WordPropertie(scanner.next());
				WordPropertie wordPropertie2 = new WordPropertie(scanner.next());
				Integer points = Integer.valueOf(scanner.next());
				WordTranslate word = new WordTranslate(wordPropertie1, wordPropertie2, points);
				return word;
			}).collect(Collectors.toList());
			in.close();
			return list;
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	public Date lastModified()
	{
		csv_file = new File(filename);
		if ((csv_file != null) && (csv_file.isFile()))
			return new Date(csv_file.lastModified());
		return null;
	}
}
