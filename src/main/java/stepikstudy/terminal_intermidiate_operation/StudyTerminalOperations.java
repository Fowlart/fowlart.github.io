package stepikstudy.terminal_intermidiate_operation;

import java.util.*;
import java.util.stream.Collectors;

public class StudyTerminalOperations {
    public static void main(String[] arg) {

        /**allMatch - return if all of elements is appropriate to condition*/
        /**anyMatch, noneMatch is working at same way*/
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        boolean rez = list.stream().allMatch((integer -> (integer > 0)));
        System.out.println(rez);


        /**collect() - operation accumulates elements in a stream into a container such as a collection*/
        Set<Integer> rezults = list.stream().collect(Collectors.toSet());
        System.out.println(rezults);

        /**count(), max(), min() - operation return long. Do not need any arguments*/
        //count()
        Integer rezult = (int) list.stream().count();
        System.out.println(rezult);
        // max()
        Optional<Integer> rezults1;
        rezults1 = list.stream().max((integer1, integer2) -> {
            if (integer1 > integer2) return 1;
            if (integer1 < integer2) return -1;
            else return 0;
        });
        System.out.println(rezults1);
        //min()
        rezults1 = list.stream().min((integer1, integer2) -> {
            if (integer1 > integer2) return 1;
            if (integer1 < integer2) return -1;
            else return 0;
        });
        System.out.println(rezults1);


    /**Trying use of Iterator. Use it instead of foreach() because of synchronizing problems */
    List<String> strings = Arrays.asList("еникі","беникі","їли","вареники");
    Iterator<String> iterator =  strings.stream().map(s->(s.toUpperCase()+" ")).iterator();
    while (iterator.hasNext()) System.out.print(iterator.next());


    }
}
