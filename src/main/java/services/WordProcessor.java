package services;

import entities.Word;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;
import java.util.Random;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class WordProcessor {

    private double progress;
    private double avgPoint;
    private int totalPoints;
    private int countOfWords;
    private int maxPoints;
    private Word word; //current

    private List<Word> wordList;

    private final Random random = new Random(47);

    public Word nextWord(List<Word> list) {

        wordList = list;

        if (wordList != null) {
            totalPoints = this.wordList.stream().mapToInt(Word::getPoints).sum();
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

    public Word getWord() {
        return word;
    }

    public void setWord(Word word) {
        this.word = word;
    }

    public Integer getMaxPoints() {
        return maxPoints;
    }

    public void setMaxPoints(Integer maxPoints) {
        this.maxPoints = maxPoints;
    }
}
