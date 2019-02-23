package stepikstudy.map_flmap_reduce_forEach;

import java.util.ArrayList;
import java.util.List;

public class Reduce {
    public static void main(String[] strings) {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i <= 7; i++) numbers.add(i);
        /** The first argument of reduce is a neutral element 0 (0 + elem = elem), the second argument is a sum function.
         * The sum function accepts two parameters: acc is an accumulated value and elem is a next element in the stream.*/
        int sum = numbers.stream().reduce(0, (acc, elem) -> {
            System.out.println("acc = " + acc + ", " + "elem = " + elem);
            return acc + elem;
        });
    }
}
