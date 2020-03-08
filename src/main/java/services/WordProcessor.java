package services;

import MVC_package.UserService;
import entities.User;
import entities.WordTranslate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;
import java.util.Random;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class WordProcessor {

    //   private User user;
    private double progress;
    private double avgPoint;
    private int totalPoints;
    private int countOfWords;
    private int maxPoints;
    private WordTranslate word; //current

    private List<WordTranslate> wordList;

    @Autowired
    private UserService userService;

    private Random random = new Random(47);

    public WordTranslate nextWord(User user) {

        wordList = userService.getDictionary(user);

        if (wordList != null) {
            totalPoints = this.wordList.stream().mapToInt(WordTranslate::getPoints).reduce(0, (i1, i2) -> i1 + i2);
            countOfWords = this.wordList.size();
            maxPoints = countOfWords * 30;
            avgPoint = (double) totalPoints / (double) countOfWords;
            progress = avgPoint / 30 * 100;

            while (true) {
                word = wordList.stream().skip(this.random.nextInt(wordList.size())).findAny().get();
                if (word.getPoints() <= (this.avgPoint)) return word;
            }
        }
        return null;
    }

    public WordTranslate nextWord(List<WordTranslate> list) {

        wordList = list;

        if (wordList != null) {
            totalPoints = this.wordList.stream().mapToInt(WordTranslate::getPoints).sum();
            countOfWords = this.wordList.size();
            maxPoints = countOfWords * 30;
            avgPoint = (double) totalPoints / (double) countOfWords;
            progress = avgPoint / 30 * 100;

            while (true) {
                word = wordList.stream().skip(this.random.nextInt(wordList.size())).findAny().get();
                if (word.getPoints() <= (this.avgPoint)) return word;
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

    public Integer getMaxPoints() {
        return maxPoints;
    }

    public void setMaxPoints(Integer maxPoints) {
        this.maxPoints = maxPoints;
    }
}
