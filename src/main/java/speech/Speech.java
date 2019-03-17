package speech;

import java.io.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;
import speech.synthesiser.SynthesiserV2;


public class Speech {

    //Create a Synthesizer instance
    public SynthesiserV2 synthesizer = new SynthesiserV2("AIzaSyBOti4mM-6x9WDnZIjIeyEU21OpBXqWBgw");


    public void speak(String text) {
        System.out.println("speaking>> " + text);
        //Create a JLayer instance
        synthesizer.setSpeed(1);
        AdvancedPlayer player = null;
        try {
            player = new AdvancedPlayer(synthesizer.getMP3Data(text));
        } catch (JavaLayerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            player.play();
        } catch (JavaLayerException e) {
            e.printStackTrace();
        }
    }

    public boolean writeToFile(String folder_path, String words) {
        try {
            List<String> word_list = Arrays.asList(words.split(" "));
            String output_file = new String(words + ".mp3");
            FileOutputStream outputStream = new FileOutputStream(new File(folder_path + "//" + output_file));
            synthesizer.setSpeed(1);
            InputStream inputStream = synthesizer.getMP3Data(word_list);
            int rez = 0;
            while (rez > -1) {
                rez = inputStream.read();
                System.out.println(rez);
                if (rez == -1) break;
                outputStream.write(rez);
            }
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public static void main(String[] args) {
        Speech speech = new Speech();
        speech.writeToFile("D:\\dictionaries", "terminate the process");

    }
}