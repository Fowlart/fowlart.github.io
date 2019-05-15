package dictionary_optimizer;

import entities.WordTranslate;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Optimizer {

    private List<WordTranslate> list;

    public Optimizer(List<WordTranslate> list) {
        this.list = list;
    }

    public List<WordTranslate> getOptimizedList() {
        Function<WordTranslate, WordTranslate> function = (w) -> {
            if (w.getPoints() >= 30) w.setPoints(28);
            return w;
        };
        return list.stream().map(function).collect(Collectors.toList());
    }
}
