package stepikstudy.practical_tasks;

import java.util.function.*;

public class Task28 {
    public static void main(String[] args){
        IntSupplier intSupplier = ()->2;
        DoubleUnaryOperator doubleUnaryOperator = Math::sin;
        BiConsumer<Integer,String> biConsumer = (x,y)->{ };
        UnaryOperator<Integer> unaryOperator = (x)->{return x+ 10;};
        BiPredicate<Integer, Integer> biPredicate =(x, y) -> (x > y) && (x % 2 == 0);
        DoubleFunction<Double> doubleFunction = (x) -> { return x + 10; };
    }
}
