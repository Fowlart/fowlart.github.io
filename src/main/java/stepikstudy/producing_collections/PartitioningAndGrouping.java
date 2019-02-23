package stepikstudy.producing_collections;

import project.entities.Item;
import project.entities.SillyTestBoard;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
public class PartitioningAndGrouping {
    public static void main(String[] strings){
        /** Partitioning is an operation that splits your data by a predicate into map of two collections.
         * The key of the map has a type Boolean.*/
        Map<Boolean, List<Item>> rez = SillyTestBoard.getTestData().stream().
                collect(Collectors.partitioningBy(((i)->i.getList().size()==0)));
        System.out.println(rez.get(true));
        /** Grouping is a more general operation than partitioning. Instead of splitting your data into two groups
         *(true and false), you can use any numbers of any groups.*/
        Map<Integer,List<Item>> gouping_rez = SillyTestBoard.getTestData().stream().collect(Collectors.
                groupingBy((i)->i.getList().size()));
        System.out.println(gouping_rez.get(1));
    }
}
