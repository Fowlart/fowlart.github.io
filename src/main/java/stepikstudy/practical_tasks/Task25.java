package stepikstudy.practical_tasks;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@FunctionalInterface
interface StringDistincter {
    List<String> getUniqueList(List<String> input);
}

public class Task25 {
    public static void main(String[] args) {
        List<String> input = Arrays.asList("Artur", "Melania", "Olenka", "Angela", "Vita", "Zenoviy", "Artur", "Artur");
        System.out.println(input.stream().distinct().collect(Collectors.joining(", ", "[", "]")));

        StringDistincter stringDistincter = (list) -> list.stream().distinct().collect(Collectors.toList());
        System.out.println(stringDistincter.getUniqueList(input));

    }
}
