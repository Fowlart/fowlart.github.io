package project.io_data_module;

import project.entities.item_implementations.words.*;

import java.io.*;
import java.io.FileReader;
import java.util.*;
import java.util.stream.Collectors;


public class CsvWordsReader {

    private String filename;

    public List<WordTranslate> getItemList(String filename) {
        this.filename = filename;
        File file = new File(filename);
        if (file.exists()) {
            try {
                BufferedReader in = new BufferedReader(new FileReader(this.filename));
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
        }
        return new ArrayList<WordTranslate>();
    }

    public String lastModified() {
        File csv_file = new File(filename);
        if ((csv_file.exists()) && (csv_file.isFile())) return new Date(csv_file.lastModified()).toString();
        return "no data";
    }
}
