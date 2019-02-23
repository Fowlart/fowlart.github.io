package stepikstudy.practical_tasks;
import java.util.Arrays;
import java.util.List;
import java.util.function.IntPredicate;
public class Task212 {
    public static IntPredicate disjunctAll(List<IntPredicate> predicates) {
        return predicates.stream().reduce((x,y)->x.or(y)).get();
    }
    public static void main(String[] strings) {
        IntPredicate intPredicate1 = x-> x>1;
        IntPredicate intPredicate2 = x-> x>2;
        IntPredicate intPredicate3 = x-> x>3;
        List<IntPredicate> list = Arrays.asList(intPredicate1,intPredicate2,intPredicate3);
        System.out.println(disjunctAll(list).test(-2));
        System.out.println(disjunctAll(list).test(-1));
        System.out.println(disjunctAll(list).test(0));
        System.out.println(disjunctAll(list).test(1));
        System.out.println(disjunctAll(list).test(2));
        System.out.println(disjunctAll(list).test(3));
        System.out.println(disjunctAll(list).test(4));
        System.out.println(disjunctAll(list).test(5));
        }
    }

