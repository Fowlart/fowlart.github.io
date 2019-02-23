package stepikstudy.stream_intro;

import java.util.Arrays;
import java.util.List;
import java.util.stream.*;

public class StreamCreating {

    public static void main(String[] args){
        /** The most common way to take a stream is to create it from a collection. Any collection has a method stream()
         * for this purpose: */
        List<Integer> famousNumbers = Arrays.asList(0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55);
        Stream<Integer> numbersStream = famousNumbers.stream();

        /**From an array*/
        Stream<Double> doubleStream = Arrays.stream(new Double[]{ 1.01, 1d, 0.99, 1.02, 1d, 0.99 });

        /**From a String*/
        IntStream stream = "aibohphobia".chars();

        /**From another streams*/
        Stream<?> concatedStream = Stream.concat(numbersStream, doubleStream);

        /**using static GENERATE*/
        DoubleStream randomStream = DoubleStream.generate(Math::random);
        randomStream.limit(5).forEach(System.out::println);

        /**Stream.iterate()*/
        IntStream oddNumbersStream = IntStream.iterate(1, x -> x + 2).limit(20);

        /**Use Stream.range() or Stream.rangeClosed(). Method rangeClosed() includes an upper bound*/
        LongStream rangedStream = LongStream.rangeClosed(100_000, 1_000_000);

    }
}
