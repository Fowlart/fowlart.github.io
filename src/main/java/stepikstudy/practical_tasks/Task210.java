package stepikstudy.practical_tasks;
@FunctionalInterface
interface TernaryIntPredicate {
   boolean test(int arg1, int arg2, int arg3);
}

public class Task210 {
    public static void main(String[] strings){
        TernaryIntPredicate ternaryIntPredicate = (x,y,z) -> (x!=y)&(y!=z)&(z!=x);
        System.out.println(ternaryIntPredicate.test(2,2,2));
    }
}
