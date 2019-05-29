package entities;

import lombok.Data;

import java.util.List;

@Data
public class User {

    //Todo need to refactor and separate nextWord() method from User entity

    // data from database
    private long id;
    private String name;
    private String password;

    private List<WordTranslate> list;
    // dynamic attributes
//    private Double progress;
//    private Double avg_point;
//    private Integer total_points;
//    private Integer count_of_words;




    public long getId() {
        return id;
    }


    public String getName() {
        return name;
    }


    public void setList(List<WordTranslate> list) {
        this.list = list;
    }

    // private Random random = new Random(47);

    public User() {
        nextWord();
    }

    public WordTranslate nextWord() {
        // recalculating view metrics
//        if (list != null) {
//            total_points = this.list.stream().mapToInt((i) -> i.getPoints()).reduce(0, (i1, i2) -> i1 + i2);
//            count_of_words = this.list.size();
//            avg_point = (double) total_points / (double) count_of_words;
//            progress = avg_point / 30 * 100;
//
//            while (true) {
//                WordTranslate wordTranslate = list.stream().skip(this.random.nextInt(list.size())).findAny().get();
//                if (wordTranslate.getPoints() <= (this.avg_point.intValue())) return wordTranslate;
//            }
//        }
        return null;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}