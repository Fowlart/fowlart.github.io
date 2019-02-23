package stepikstudy.producing_collections;

import project.entities.Item;
import project.entities.SillyTestBoard;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Value_Producer {
    public static void main(String[] strings){
        List<Item> list = SillyTestBoard.getTestData();
        System.out.println(list.stream().collect(Collectors.maxBy((i1,i2)->i1.getPrice().compareTo(i2.getPrice()))).
                get());
        System.out.println(list.stream().collect(Collectors.minBy((i1,i2)->i1.getPrice().compareTo(i2.getPrice()))).
                get());
        System.out.println(list.stream().collect(Collectors.averagingDouble((i)->i.getPrice().doubleValue())));

        BinaryOperator<String> binaryOperator =(s1,s2)->s1.concat(s2).
                replaceFirst(", ","");
        Function<Item,String> function = (i) ->i.getName();

        String all_items_name =  list.stream().collect(Collectors.reducing("",function,binaryOperator));
        System.out.println(all_items_name);

    }
}