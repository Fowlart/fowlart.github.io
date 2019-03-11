package project.entities.item_implementations.words;

public class WordPropertie {

    private String word;
    private WordsProperieType type;

    public WordPropertie(String word) {
        setWord(word);
    }

    private Boolean isConteinsLatin() {
        char[] mas = this.word.toCharArray();
        for (Character s : mas) {
            if (s.toString().matches("[A-Za-z]")) return true;
        }
        return false;
    }

    private void setWord(String word) {
        this.word = word.trim();
        type = WordsProperieType.UNDEFINED;
        if (isConteinsLatin()) type = WordsProperieType.ENG;
        if (!isConteinsLatin()) type = WordsProperieType.UKR;
    }

    public String getPropertieValue() {
        return word;
    }

    public WordsProperieType getType() {
        return type;
    }
}
