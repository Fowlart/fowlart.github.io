package project.entities.item_implementations.words;

import project.entities.Item;
import project.entities.ItemProperty;

import java.util.Arrays;


public class WordTranslate extends Item {
    private String engword;
    private String ukrword;
    private Integer points;

    public WordTranslate(WordPropertie word1, WordPropertie word2, Integer points ) {
        this.points = points;
        if (word1.getType() == WordsProperieType.UKR) {
            ukrword = word1.getPropertieValue();
            engword = word2.getPropertieValue();
        } else if (word1.getType() == WordsProperieType.ENG) {
            ukrword = word2.getPropertieValue();
            engword = word1.getPropertieValue();
        } else {
            ukrword = engword = WordsProperieType.UNDEFINED.name();
        }
    }

    public String getEngword() {
        return engword.toLowerCase();
    }

    public String getUkrword() {
        return ukrword.toLowerCase();
    }

    public Integer getPoints() { return points; }
}
