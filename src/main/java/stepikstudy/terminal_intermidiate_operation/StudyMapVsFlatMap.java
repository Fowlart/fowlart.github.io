package stepikstudy.terminal_intermidiate_operation;

import java.util.*;
import java.util.stream.Collectors;

public class StudyMapVsFlatMap {
    public static void main(String[] args) {

        /**Fun task*/
        List<String> list = Arrays.asList("STTTTTAAAACCCCK", "OOOVVVEEEERRRR");

        List<String> rez = list.stream().map((w -> w.split(""))).map(
                mas -> Arrays.stream(mas).distinct().collect(Collectors.joining())).collect(Collectors.toList());

        System.out.println(rez);

        /**
         * Контракт і сигнатура метода flatMap
         *  <R> Stream<R> flatMap(Function<? super T, ? extends Stream<? extends R>> mapper);
         *передбачає перетворення елемента на Stream чогось і повертає результуючий stream тієї всієї фігні
         */
        List<String> characters = list.stream().flatMap((w -> Arrays.stream(w.split(""))))
                .distinct().
                        collect(Collectors.toList());
        System.out.println(characters);

    }
}
