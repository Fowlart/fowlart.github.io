package speech;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;
import project.entities.item_implementations.words.WordTranslate;
import speech.synthesiser.SynthesiserV2;

import java.io.*;
import java.util.Arrays;
import java.util.List;


public class Speech
{

	//Create a Synthesizer instance
	private SynthesiserV2 synthesizer = new SynthesiserV2("AIzaSyBOti4mM-6x9WDnZIjIeyEU21OpBXqWBgw");


	public File getSingleMp3(String word, String output_file, String code) throws IOException
	{
		synthesizer.setLanguage(code);
		synthesizer.setSpeed(1);
		InputStream inputStream = synthesizer.getMP3Data(word);

		File file = new File(output_file);
		//if (!file.exists()) file.createNewFile();
		FileOutputStream outputStream = new FileOutputStream(file);
		int rez = 0;
		while (rez > -1)
		{
			rez = inputStream.read();
			outputStream.write(rez);
		}
		outputStream.flush();
		outputStream.close();
		return file;
	}

	public void speak(String text, String language_code)
	{
		synthesizer.setLanguage(language_code);
		System.out.println("speaking>> " + text);
		//Create a JLayer instance
		synthesizer.setSpeed(1);
		AdvancedPlayer player = null;
		try
		{
			player = new AdvancedPlayer(synthesizer.getMP3Data(text));
			player.play();
		}
		catch (JavaLayerException | IOException e)
		{
			e.printStackTrace();
		}
	}

	public void writeToFile(String folder_path, WordTranslate word)
	{
		Boolean rez = false;
		int try_count = 0;

		while (!rez && (try_count < 5))
		{
			try_count++;
			rez = processWord(folder_path, word);
		}
	}

	private boolean processWord(String folder_path, WordTranslate word)
	{
		this.synthesizer.setLanguage("en-us");
		String output_file = word + ".mp3";
		try
		{
			process(folder_path, word.getEngword(), output_file);
			this.synthesizer.setLanguage("uk");
			String output_file_ukr = word.getEngword() + "_ukr" + ".mp3";
			process(folder_path, word.getUkrword(), output_file_ukr);
		}
		catch (IOException e)
		{
			e.printStackTrace();
			return false;
		}
		return true;
	}

	private void process(String folder_path, String word, String output_file) throws IOException
	{
		List<String> word_list = Arrays.asList(word.split(" "));
		FileOutputStream outputStream = new FileOutputStream(new File(folder_path + "//" + output_file));
		synthesizer.setSpeed(1);
		InputStream inputStream = synthesizer.getMP3Data(word_list);
		int rez = 0;
		while (rez > -1)
		{
			rez = inputStream.read();
			outputStream.write(rez);
		}
		System.out.println("file " + output_file + " was created");
		outputStream.flush();
		outputStream.close();
	}
}
