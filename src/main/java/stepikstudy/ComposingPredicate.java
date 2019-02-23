package stepikstudy;

import java.util.ArrayList;
import java.util.List;
import java.util.function.IntPredicate;
public class ComposingPredicate {
    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i <= 30; i++) numbers.add(i);
        IntPredicate isEven = x -> x % 2 == 0;
        IntPredicate dividedBy3 = x -> x % 3 == 0;
        IntPredicate combined_predicate = isEven.negate().and(dividedBy3);
        // print all odd values that can be divided by 3.
        numbers.forEach(val -> {
            if (combined_predicate.test(val)) System.out.print(val + " ");
        });
    }
}
