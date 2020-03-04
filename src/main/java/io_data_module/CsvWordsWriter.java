package io_data_module;

import entities.WordTranslate;

import java.io.UnsupportedEncodingException;
import java.util.List;

public class CsvWordsWriter {


    public String getCsv(List<WordTranslate> list) throws UnsupportedEncodingException {

        StringBuilder csv = new StringBuilder();

        for (WordTranslate wordTranslate : list) {
            csv.append(wordTranslate.getEngword() + ";" + wordTranslate.getUkrword() +
                    ";" + wordTranslate.getPoints() + System.lineSeparator());
        }
        return csv.toString().trim();
    }
}