package io_data_module;

import entities.Word;

import java.io.UnsupportedEncodingException;
import java.util.List;

public class CsvWordsWriter {


    public String getCsv(List<Word> list) throws UnsupportedEncodingException {

        StringBuilder csv = new StringBuilder();

        for (Word word : list) {
            csv.append(word.getEngword() + ";" + word.getUkrword() +
                    ";" + word.getPoints() + System.lineSeparator());
        }
        return csv.toString().trim();
    }
}