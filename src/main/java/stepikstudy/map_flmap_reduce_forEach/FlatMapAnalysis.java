package stepikstudy.map_flmap_reduce_forEach;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Book {
    private String name;
    private int year;
    private List<String> authors;

    Book(String name, int year, List<String> authors) {
        this.name = name;
        this.year = year;
        this.authors = authors;
    }

    public List<String> getAuthors() {
        return authors;
    }
}

public class FlatMapAnalysis {
    public static void main(String[] strings) {
        /**The flatMap operation is used to transform each element from a stream into another stream and concatenates
         **all streams into one. For instance, to obtain elements of an internal collection in a class.**/
        // the collection of java books
        final List<Book> javaBooks = Stream.of(
                new Book("Java EE 7 Essentials", 2013, Arrays.asList("Arun Gupta", "Artur Semikov")),
                new Book("Algorithms", 2011, Arrays.asList("Robert Sedgewick", "Kevin Wayne", "Artur Semikov")),
                new Book("Clean code", 2014, Arrays.asList("Robert Martin", "Artur Semikov")),
                new Book("Safety craft", 2025, Arrays.asList("Artur Semikov"))
        ).collect(Collectors.toList());
        // list of authors
        final List<String> authors = javaBooks.stream()
                .flatMap(book -> book.getAuthors().stream())
                .distinct()
                .collect(Collectors.toList());
        System.out.println(authors);
    }
}
