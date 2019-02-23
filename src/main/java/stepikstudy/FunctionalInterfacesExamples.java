package stepikstudy;

import java.util.function.Consumer;
import java.util.function.IntPredicate;
import java.util.function.IntSupplier;
import java.util.function.IntUnaryOperator;

public class FunctionalInterfacesExamples {
    public static void main(String[] args) {

        // generator accepts nothing and returns integer value 3
        IntSupplier generator = () -> 3;

        final int factor = 2;

        // multiplier accepts an integer value and returns another one, it uses closure
        IntUnaryOperator multiplier = val -> factor * val;

        // predicate isEven accepts an integer value and returns true if the value is even else false
        IntPredicate isEven = val -> val % 2 == 0;

        // printer accepts a value and prints it in the standard out, it returns nothing
        Consumer<String> printer = System.out::println;

        //considering usage for all:
        System.out.println(generator.getAsInt());
        System.out.println(multiplier.applyAsInt(10));
        System.out.println(isEven.test(9));
        printer.accept("PrInT SoME TeXt");
    }}
