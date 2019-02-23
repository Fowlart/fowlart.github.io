package stepikstudy.practical_tasks;
/** Write a lambda expression that accepts seven (!) string arguments and returns a string in
 * upper case concatenated from all of them (in the order of arguments).*/
@FunctionalInterface
interface StringConcatable {
    String concat(String arg1, String arg2, String arg3, String arg4, String arg5, String arg6, String arg7);
}
public class Task23 {
    public static void main(String[] args) {
        StringConcatable implement = (s1, s2, s3, s4, s5, s6, s7) -> (s1 + s2 + s3 + s4 + s5 + s6 + s7).toUpperCase();
        System.out.println(implement.concat("1", "2", "3", "4", "5", "6", "7"));
    }
}