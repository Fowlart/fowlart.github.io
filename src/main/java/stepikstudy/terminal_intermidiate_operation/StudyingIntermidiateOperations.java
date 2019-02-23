package stepikstudy.terminal_intermidiate_operation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * For studying Stream API
 */
public class StudyingIntermidiateOperations {

    public static void main(String[] args) {
        /**Creating stream of Integers*/
        Stream<Integer> streamIterated = Stream.iterate(40, n -> n + 2).limit(30);

        /**try filter(Predicate)*/
        // рахуємо всі парні числа, має бути 30шт в потоці
        long count = streamIterated.filter(x -> x % 2 == 0).count();
        System.out.println(count);

        /**try distinct()*/
        // рахуємо всі унікальні числа в потоці
        streamIterated = Stream.of(1, 2, 2, 2, 3, 3, 4, 4, 4, 5, 6, 6, 6);
        long count2 = streamIterated.distinct().count();
        System.out.println(count2);

        /**try limit(), skip()*/
        // створюємо безкінечний рандомний потік інтів за допомогою генератора;
        // виставляємо limit на потік і skip-аємо певну кількість елементів
        final Random rand = new Random(47);
        IntStream stream = IntStream.generate(() -> rand.nextInt(200));
        System.out.println( Arrays.toString( stream.limit(210).skip(190).toArray() ) );

        /**try sorted() - without Comparator*/
        stream = IntStream.generate(() -> rand.nextInt(200)).limit(200);
        System.out.println( Arrays.toString( stream.sorted().skip(90).toArray()));


        /**using map()*/
        stream = IntStream.generate(() -> rand.nextInt(200)).limit(5);
        System.out.println(Arrays.toString( stream.map((x)->x*100).toArray() ));


        /**peek() - for debugging operations only*/
        Stream<Integer> atherStream = Stream.generate(() -> rand.nextInt(200)).limit(20);
        Consumer<Integer> consumer = (integer)-> System.out.println("picked: "+integer);
        Consumer<Integer> voidconsumer = (integer)->{};
        atherStream.peek(consumer).forEach(voidconsumer);
    }
}
