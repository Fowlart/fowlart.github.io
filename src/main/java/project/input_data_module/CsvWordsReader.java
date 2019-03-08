package project.input_data_module;

import project.entities.item_implementations.words.*;

import java.io.*;
import java.io.FileReader;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CsvWordsReader {
    public List<WordTranslate> getItemList(String filename) {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader( new FileInputStream(new File(filename)),
                    "cp1251"));
            return in.lines().map((s) -> {
                Scanner scanner = new Scanner(s);
                scanner.useDelimiter(";");
                WordPropertie wordPropertie1 = new WordPropertie(scanner.next());
                WordPropertie wordPropertie2 = new WordPropertie(scanner.next());
                Integer points = Integer.valueOf(scanner.next());
                WordTranslate word = new WordTranslate(wordPropertie1, wordPropertie2,points);
                return  word;
            }).collect(Collectors.toList());

        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}