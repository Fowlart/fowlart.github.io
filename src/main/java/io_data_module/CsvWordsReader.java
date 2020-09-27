package io_data_module;

import entities.WordPropertie;
import entities.WordTranslate;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;


public class CsvWordsReader {

    private String filename;

    public List<WordTranslate> getItemListFromFile(byte[] bytes) {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(bytes)));

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
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public List<WordTranslate> getItemList(String filename) {
        this.filename = filename;
        File file = new File(filename);
        if (file.exists()) {
            try {
                BufferedReader in = new BufferedReader(new FileReader(this.filename));
                List<WordTranslate> list = in.lines().map((s) -> {
                    Scanner scanner = new Scanner(s);
                    scanner.useDelimiter(";");
                    WordPropertie wordPropertie1 = new WordPropertie(scanner.next());
                    WordPropertie wordPropertie2 = new WordPropertie(scanner.next());
                    Integer points = Integer.valueOf(scanner.next());
                    WordTranslate word = new WordTranslate(wordPropertie1, wordPropertie2, points);
                    return word;
                }).collect(Collectors.toList());
                in.close();
                return list;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new ArrayList<>();
    }

    public String lastModified() {
        File csv_file = new File(filename);
        DateFormat df = DateFormat.getDateTimeInstance();
        if ((csv_file.exists()) && (csv_file.isFile())) return df.format(new Date(csv_file.lastModified()));
        return "no data";
    }
}
