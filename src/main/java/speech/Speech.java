package speech;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;
import project.entities.item_implementations.words.WordPropertie;
import project.entities.item_implementations.words.WordTranslate;
import speech.synthesiser.SynthesiserV2;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;


public class Speech
{

	//Create a Synthesizer instance
	private SynthesiserV2 synthesizer = new SynthesiserV2("AIzaSyBOti4mM-6x9WDnZIjIeyEU21OpBXqWBgw");


	public void speak(String text)
	{
		System.out.println("speaking>> " + text);
		//Create a JLayer instance
		synthesizer.setSpeed(1);
		AdvancedPlayer player = null;
		try
		{
			player = new AdvancedPlayer(synthesizer.getMP3Data(text));
		}
		catch (JavaLayerException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		try
		{
			player.play();
		}
		catch (JavaLayerException e)
		{
			e.printStackTrace();
		}
	}

	public void writeToFile(String folder_path, WordTranslate word)
	{
		processWord(folder_path, word.getEngword());
		processUkrWord(folder_path,word);
	}

	private boolean processWord(String folder_path, String word)
	{
		try
		{
			List<String> word_list = Arrays.asList(word.split(" "));
			String output_file = new String(word + ".mp3");
			FileOutputStream outputStream = new FileOutputStream(new File(folder_path + "//" + output_file));
			synthesizer.setSpeed(1);
			InputStream inputStream = synthesizer.getMP3Data(word_list);
			int rez = 0;
			while (rez > -1)
			{
				rez = inputStream.read();
				System.out.print(rez+" ");
				outputStream.write(rez);
			}
			System.out.println();
			outputStream.flush();
			outputStream.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return true;
	}

	private boolean processUkrWord(String folder_path, WordTranslate wordTranslate)
	{
		try
		{
			List<String> word_list = Arrays.asList(wordTranslate.getUkrword().split(" "));
			String output_file = new String(wordTranslate.getEngword()+"_ukr" + ".mp3");
			FileOutputStream outputStream = new FileOutputStream(new File(folder_path + "//" + output_file));
			synthesizer.setSpeed(1);
			InputStream inputStream = synthesizer.getMP3Data(word_list);
			int rez = 0;
			while (rez > -1)
			{
				rez = inputStream.read();
				System.out.print(rez+" ");
				if (rez == -1)
					break;
				outputStream.write(rez);
			}
			System.out.println();
			outputStream.flush();
			outputStream.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return true;
	}

	public static void main(String[] args)
	{
		Speech speech = new Speech();
		WordTranslate dog = new WordTranslate(new WordPropertie("Cat"),new WordPropertie("Кішка"),0);
		speech.writeToFile("c:\\dictionaries", dog);

	}
}
