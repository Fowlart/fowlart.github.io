package stepikstudy.parallel_streams;

import java.util.Arrays;
import java.util.List;

public class HowToCall {
    public static void main(String[] strings) {
        List<String> languages = Arrays.asList("-java-", "-scala-", "-kotlin-", "-clojure-", "-C#-");
        List<String> must_have = Arrays.asList("-english-","-python-","-js-","-html-","-css-");
        languages.parallelStream().parallel().forEach(s -> System.out.print(s));
        must_have.stream().forEach(s -> System.out.print(s));
    }
}