package services;

import entities.Sentence;
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
    private Sentence sentence; //current

    private List<Sentence> sentenceList;

    private final Random random = new Random(47);

    public Sentence nextWord(List<Sentence> list) {

        sentenceList = list;

        if (sentenceList != null) {
            totalPoints = this.sentenceList.stream().mapToInt(Sentence::getPoints).sum();
            countOfWords = this.sentenceList.size();
            maxPoints = countOfWords * 30;
            avgPoint = (double) totalPoints / (double) countOfWords;
            progress = avgPoint / 30 * 100;

            while (true) {
                sentence = sentenceList.stream().skip(this.random.nextInt(sentenceList.size())).findAny().get();
                if (sentence.getPoints() <= (this.avgPoint)) return sentence;
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

    public Sentence getWord() {
        return sentence;
    }

    public void setWord(Sentence sentence) {
        this.sentence = sentence;
    }

    public Integer getMaxPoints() {
        return maxPoints;
    }

    public void setMaxPoints(Integer maxPoints) {
        this.maxPoints = maxPoints;
    }
}
