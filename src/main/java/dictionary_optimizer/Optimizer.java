package dictionary_optimizer;

import entities.Word;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Optimizer {

    private final List<Word> list;

    public Optimizer(List<Word> list) {
        this.list = list;
    }

    public List<Word> getOptimizedList() {
        Function<Word, Word> function = (w) -> {
            if (w.getPoints() >= 30) w.setPoints(28);
            return w;
        };
        return list.stream().map(function).collect(Collectors.toList());
    }
}
