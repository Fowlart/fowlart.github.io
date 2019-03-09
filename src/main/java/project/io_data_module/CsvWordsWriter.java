package project.io_data_module;

import com.sun.faces.facelets.util.Path;
import project.entities.item_implementations.words.WordTranslate;

import java.io.*;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class CsvWordsWriter {

    BufferedWriter writer;

    public boolean writeInFile(String filename, List<WordTranslate> list) {

        File file = new File(filename);

        try {
            if (!file.exists()) file.createNewFile();
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename), "CP1251"));
            for (WordTranslate wordTranslate : list) {
                writer.write(wordTranslate.getEngword() + ";" + wordTranslate.getUkrword() +
                        ";" + wordTranslate.getPoints());
                writer.newLine();
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println("EXCEPTION: " + e.getMessage());
            return false;
        }
        return true;
    }
}