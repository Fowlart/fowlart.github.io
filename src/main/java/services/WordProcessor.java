package services;

import data_base.WordTranslateRepository;
import entities.User;
import entities.WordTranslate;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Random;

@Data
public class WordProcessor {

    private User user;
    private Double progress;
    private Double avg_point;
    private Integer total_points;
    private Integer count_of_words;

    private List<WordTranslate> word_list;

    private WordTranslateRepository wordTranslateRepository;
    private UserService userService;

    private Random random = new Random(47);

    @Autowired
    public WordProcessor(WordTranslateRepository wordTranslateRepository, UserService userService, User user) {
        this.user = user;
        this.userService = userService;
        this.wordTranslateRepository = wordTranslateRepository;
        word_list = userService.getDictionary(user);
    }

    public WordTranslate nextWord() {

        if (word_list != null) {
            total_points = this.word_list.stream().mapToInt((i) -> i.getPoints()).reduce(0, (i1, i2) -> i1 + i2);
            count_of_words = this.word_list.size();
            avg_point = (double) total_points / (double) count_of_words;
            progress = avg_point / 30 * 100;

            while (true) {
                WordTranslate wordTranslate = word_list.stream().skip(this.random.nextInt(word_list.size())).findAny().get();
                if (wordTranslate.getPoints() <= (this.avg_point.intValue())) return wordTranslate;
            }
        }
        return null;
    }



}
