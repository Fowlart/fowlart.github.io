package stepikstudy.practical_tasks;
import java.util.function.ToLongBiFunction;
import java.util.stream.LongStream;
/***
 * Write a lambda expression that accepts two long arguments as a range borders and calculates (returns) production
 * of all numbers in this range (inclusively). It's guaranteed that 0 <= left border <= right border. if left border ==
 * right border then the result is any border.
 ***/
public class Task24 {
    public static void main(String[] args) {
        /**my code without Stream api use*/
        ToLongBiFunction<Long, Long> myfunction = (Long x, Long y) -> {
            long result = 1;
            for (long index = x; index <= y; index++) {
                result = result * index;
            }
            return result;
        };
        /**code example with Stream api use */
       // myfunction = (l, r) -> LongStream.rangeClosed(l, r).reduce(1L, (acc, x) -> acc * x);
        System.out.println(myfunction.applyAsLong(Long.valueOf(2), Long.valueOf(4)));
    }
}