package project.input_data_module;
import project.entities.Item;
import java.util.List;

public interface FileReader {
    List<? extends Item> getItemList(String filename);
}
