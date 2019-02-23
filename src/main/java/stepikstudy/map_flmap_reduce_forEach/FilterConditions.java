package stepikstudy.map_flmap_reduce_forEach;
/**
 * class for using multiple filters
 */

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class FilterConditions {
    public FilterConditions() {
        this.predicateList = new ArrayList<>();
    }

    private List<Predicate> predicateList;
    public void addToPredicateList(Predicate intPredicate) {
        predicateList.add(intPredicate);
    }

    public Predicate<Integer> negateEachAndConjunctAll() {
        BinaryOperator<Predicate> binaryOperator = new BinaryOperator<Predicate>() {
            @Override
            public Predicate apply(Predicate predicate, Predicate predicate2) {
               return predicate.and(predicate2);
            }
        };
        return predicateList.stream().reduce(binaryOperator).get();
    }

    public static void main(String[] strings) {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i <= 1000; i++) numbers.add(i);
        FilterConditions filter = new FilterConditions();

        Predicate<Integer> predicate1 = (x) -> x < 20;
        Predicate<Integer> predicate2 = (x) -> x>10;
        Predicate<Integer> predicate3 = (x)->((x % 2) == 0);

        filter.addToPredicateList(predicate1);
        filter.addToPredicateList(predicate2);
        filter.addToPredicateList(predicate3);

        //getting summary filter condition
        numbers.stream().flatMap((x) -> Stream.of(x)).filter(filter.negateEachAndConjunctAll()).peek(System.out::println)
                .count();


    }
}