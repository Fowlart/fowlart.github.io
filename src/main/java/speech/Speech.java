package speech;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;
import project.entities.item_implementations.words.WordTranslate;
import speech.synthesiser.SynthesiserV2;

import java.io.*;
import java.util.Arrays;
import java.util.List;


public class Speech {

    //Create a Synthesizer instance
    private SynthesiserV2 synthesizer = new SynthesiserV2("AIzaSyBOti4mM-6x9WDnZIjIeyEU21OpBXqWBgw");


    public void setLanguage(String code) {
        synthesizer.setLanguage(code);
    }

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

    public void writeToFile(String folder_path, WordTranslate word) {
        Boolean rez = false;
        int try_count = 0;

        while (!rez && (try_count < 5)) {
            try_count++;
            rez = processWord(folder_path, word.getEngword());
        }
        try_count = 0;
        rez = false;

        while (!rez && (try_count < 5)) {
            try_count++;
            rez = processUkrWord(folder_path, word);
        }
    }

    private boolean processWord(String folder_path, String word) {
        this.synthesizer.setLanguage("en-us");
        String output_file = word + ".mp3";
        try {
            process(folder_path, word, output_file);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private void process(String folder_path, String word, String output_file) throws IOException {
        List<String> word_list = Arrays.asList(word.split(" "));
        FileOutputStream outputStream = new FileOutputStream(new File(folder_path + "//" + output_file));
        synthesizer.setSpeed(1);
        InputStream inputStream = synthesizer.getMP3Data(word_list);
        int rez = 0;
        while (rez > -1) {
            rez = inputStream.read();
            outputStream.write(rez);
        }
        System.out.println("file " + output_file + " was created");
        outputStream.flush();
        outputStream.close();
    }


    private boolean processUkrWord(String folder_path, WordTranslate wordTranslate) {
        this.synthesizer.setLanguage("uk");
        try {
            String output_file = new String(wordTranslate.getEngword() + "_ukr" + ".mp3");
            process(folder_path, wordTranslate.getUkrword(), output_file);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}