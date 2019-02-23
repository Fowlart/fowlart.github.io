package stepikstudy;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * In computer programming, a callback is a piece of executable code that is passed as an argument to other code,
 * which is expected to call back (execute) the argument at some convenient time
 */

public class Callbacks {
    public static void main(String[] args) {

        // our collection with fruits
        List<String> fruits = Arrays.asList("apple", "pear", "orange", "banana");

        // it will print all fruits in the collection
        // using static function
        fruits.forEach(System.out::println);

        Consumer<String> consumer = fruit -> {
            String doubleFruit = fruit.concat(fruit).toUpperCase();
            if (doubleFruit.length() > 10) {
                System.out.println(doubleFruit);
            }
        };

        fruits.forEach(consumer);
    }
}
