package io_data_module;

import entities.WordTranslate;

import java.io.*;
import java.util.List;

public class CsvWordsWriter {

    BufferedWriter writer;

    public boolean writeInFile(String filename, List<WordTranslate> list) {

        File file = new File(filename);

        try {
            if (!file.exists()) file.createNewFile();
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename)));
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