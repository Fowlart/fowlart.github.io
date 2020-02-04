package services;

import MVC_package.UserService;
import data_base.WordTranslateRepository;
import entities.User;
import entities.WordTranslate;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Random;

@Data
public class WordProcessor {

    //   private User user;
    private Double progress;
    private Double avgPoint;
    private Integer totalPoints;
    private Integer countOfWords;
    private WordTranslate word;

    private List<WordTranslate> wordList;

    @Autowired
    private UserService userService;

    private Random random = new Random(47);

    public WordTranslate nextWord(User user) {

        wordList = userService.getDictionary(user);

        if (wordList != null) {
            totalPoints = this.wordList.stream().mapToInt(WordTranslate::getPoints).reduce(0, (i1, i2) -> i1 + i2);
            countOfWords = this.wordList.size();
            avgPoint = (double) totalPoints / (double) countOfWords;
            progress = avgPoint / 30 * 100;

            while (true) {
                word = wordList.stream().skip(this.random.nextInt(wordList.size())).findAny().get();
                if (word.getPoints() <= (this.avgPoint.intValue())) return word;
            }
        }
        return null;
    }

    public Double getProgress() {
        return progress;
    }

    public void setProgress(Double progress) {
        this.progress = progress;
    }

    public Double getAvgPoint() {
        return avgPoint;
    }

    public void setAvgPoint(Double avgPoint) {
        this.avgPoint = avgPoint;
    }

    public Integer getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(Integer totalPoints) {
        this.totalPoints = totalPoints;
    }

    public Integer getCountOfWords() {
        return countOfWords;
    }

    public void setCountOfWords(Integer countOfWords) {
        this.countOfWords = countOfWords;
    }

    public WordTranslate getWord() {
        return word;
    }

    public void setWord(WordTranslate word) {
        this.word = word;
    }
}
