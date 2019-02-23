package stepikstudy.terminal_intermidiate_operation;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

/**
 * https://www.youtube.com/watch?v=O8oN4KSZEXE&index=4&t=3565s&list=PLc5rKYut0ys21MJuy1K8uHmtDb5kdZ7-C, 47-ма хвилина
 **/
public class MyReduce {
    /**
     * Метод reduce принимает  лямбда-выражение известное как аккумулятор (Accumulator),которое служит для
     * сворачивания данных в одну "кучу".
     */
    static void countSum() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 5);
        Optional<Integer> sum = numbers.stream().reduce((left, right) -> left + right);
        sum.ifPresent(System.out::println);
    }

    /**
     * Перегруженный метод reduce принимает начальное значение (identity) и аккумулятор.
     * В первом случае результат метода reduce вернул Optional<Integer> т.к. мы не указывали начальное значение.
     * Во втором случае мы указали начальное значение, и метод reduce уже возвращает обычный Integer.
     */
    static void countSum1() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 5);
        Integer sum = numbers.stream().reduce(100, (left, right) -> left + right);
        System.out.println(sum);
    }

    /**
     * ???
     * <p>
     * В данном случае метод reduce принимает три параметра - identity, accumulator, combiner. Где accumulator умножает
     * каждое значение из Stream-a на начальное значение (identity) а combiner собирает результат работы accumulator.
     */
    static void complexCount() {
        List<Integer> numbers = Arrays.asList(2, 5, 8);

        // 2*1 + 5*1 + 8*1
        Integer sum = numbers.stream().reduce(1,
                (identity, val) -> identity * val,
                (left, right) -> left + right);

        System.out.println(sum);
        //output 60
    }

    static void alterExample() {
        {
            // creating a list of Strings
            List<String> words = Arrays.asList("GFG", "Geeks", "for", "GeeksQuiz", "GeeksforGeeks");

            // The lambda expression passed to
            // reduce() method takes two Strings
            // and returns the the longer String.
            // The result of the reduce() method is
            // an Optional because the list on which
            // reduce() is called may be empty.
            Optional<String> longestString = words.stream().reduce((word1, word2)
                    -> word1.length() > word2.length() ? word1 : word2);
            // Displaying the longest String
            longestString.ifPresent(System.out::println);
        }
    }

    public static void getProduct() {
        /** To get the product(a quantity obtained by multiplying quantities together, or from an analogous algebraic
         *operation)of all elements in given range excluding the rightmost element*/
        int product = IntStream.range(1, 1)
                .reduce((num1, num2) -> num1 * num2)
                .orElse(-1);

        // Displaying the product
        System.out.println("The product is : " + product);
    }

    /**Combiner method is used in parallel streams... */
    /**<U> U reduce(U identity,    BiFunction<U,? super T,U> accumulator,    BinaryOperator<U> combiner)*/


    public static void getFullyReducer() {
        int length = Arrays.asList("str1", "str2", "str3").stream()
                .reduce(0, (Integer accumulatedInt, String str) -> accumulatedInt + str.length(),
                        (accumulatedInt, accumulatedInt2) -> accumulatedInt + accumulatedInt2);

        System.out.println(length);
    }

    public static void main(String[] args) {
        // countSum();
        // countSum1();
        // complexCount();
        // alterExample();
        // getProduct();
        getFullyReducer();
    }
}
