package project.data_processing_lab;
import project.entities.Item;
import project.input_data_module.CsvReader;
/** Main task of this class is making experiment's for processing data(different goods for selling in this case)*/
public class Lab {
    public static void main(String[] strings){
        CsvReader csvReader = new CsvReader();
        // Item with the highest price
        System.out.println(csvReader.getItemList("").stream().
                max((i1,i2)->{return ((Item) i1).getPrice().compareTo(((Item) i2).getPrice());}).get());
        System.out.println(csvReader.getItemList("").stream().
                min((i1,i2)->{return ((Item) i1).getPrice().compareTo(((Item) i2).getPrice());}).get());
    }
}