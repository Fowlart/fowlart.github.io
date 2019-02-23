package stepikstudy.producing_collections;
import project.entities.SillyTestBoard;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
public class Example {
    public static void main(String[] strings) {
        /** collect to list, set, map*/
        List item_list = SillyTestBoard.getTestData().stream().map((i)->i.getName()).collect(Collectors.toList());
        System.out.println(item_list);
        Set item_set = SillyTestBoard.getTestData().stream().map((i)->i.getName()).collect(Collectors.toSet());
        System.out.println(item_set);
        Map<String, BigDecimal> item_map = SillyTestBoard.getTestData().stream().collect(Collectors.toMap
                (i->i.getName(),i->i.getPrice()));
        System.out.println(item_map);
    }
}
