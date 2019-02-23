package stepikstudy;
import java.util.function.Function;

public class ComposingFunctions {
    public static void main(String [] args) {
        Function<Integer, Integer> adder = x -> x + 10;
        Function<Integer, Integer> multiplier = x -> x * 5;

        // compose: adder(multiplier(5))
        // спочатку виконується multiplier(5) - множення на 5, а потім adder(25)
        System.out.println("result: " + adder.compose(multiplier).apply(5));
        // andThen: multiplier(adder(5))
        // спочатку виконується adder(5), потім multiplier(15)
        System.out.println("result: " + adder.andThen(multiplier).apply(5));

        System.out.println(multiplier.apply(adder.apply(5)));
    }
}
