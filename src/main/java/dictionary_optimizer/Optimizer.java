package dictionary_optimizer;

import entities.Sentence;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Optimizer {

    private final List<Sentence> list;

    public Optimizer(List<Sentence> list) {
        this.list = list;
    }

    public List<Sentence> getOptimizedList() {
        Function<Sentence, Sentence> function = (w) -> {
            if (w.getPoints() >= 30) w.setPoints(28);
            return w;
        };
        return list.stream().map(function).collect(Collectors.toList());
    }
}
