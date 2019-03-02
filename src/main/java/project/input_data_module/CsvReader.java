package project.input_data_module;

import project.entities.GoodsItem;
import project.entities.Item;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
/** Created for test, with import data from csv */
public class CsvReader implements project.input_data_module.FileReader {

    @Override
    public List<? extends Item> getItemList(String filename) {
        return getTestList(filename);
    }

    private List<GoodsItem> getTestList(String filename) {
        try {
            BufferedReader in = new BufferedReader(new FileReader(filename));
            return in.lines().map((s) -> {
                Scanner scanner = new Scanner(s);
                scanner.useDelimiter(";");
                GoodsItem goodsItem = new GoodsItem();
                goodsItem.setName(scanner.next());
                goodsItem.setPrice(BigDecimal.valueOf(Double.valueOf(scanner.next().replaceAll(",","."))));
                return goodsItem;
            }).collect(Collectors.toList());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    return null;
    }
}