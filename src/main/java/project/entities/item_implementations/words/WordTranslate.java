package project.entities.item_implementations.words;

import java.util.Objects;


public class WordTranslate {
    private String engword;
    private String ukrword;
    private Integer points;

    public WordTranslate(WordPropertie word1, WordPropertie word2, Integer points ) {
        this.points = points;
        if (word1.getType() == WordsPropertyTypes.UKR) {
            ukrword = word1.getPropertieValue();
            engword = word2.getPropertieValue();
        }

        else if (word1.getType() == WordsPropertyTypes.ENG) {
            ukrword = word2.getPropertieValue();
            engword = word1.getPropertieValue();
        } else {
            ukrword = engword = WordsPropertyTypes.UNDEFINED.name();
        }
    }

    public String getEngword() {
        return engword.toLowerCase();
    }

    public String getUkrword() {
        return ukrword.toLowerCase();
    }

    public Integer getPoints() { return points; }

    public void setPoints(Integer points) {
        this.points = points;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WordTranslate)) return false;
        WordTranslate that = (WordTranslate) o;
        return Objects.equals(engword, that.engword) &&
                Objects.equals(ukrword, that.ukrword) &&
                Objects.equals(points, that.points);
    }

    @Override
    public int hashCode() {
        return Objects.hash(engword, ukrword, points);
    }
}
