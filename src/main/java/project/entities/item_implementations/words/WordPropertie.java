package project.entities.item_implementations.words;

public class WordPropertie {

    private String word;
    private WordsPropertyTypes type;

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
        type = WordsPropertyTypes.UNDEFINED;
        if (isConteinsLatin()) type = WordsPropertyTypes.ENG;
        if (!isConteinsLatin()) type = WordsPropertyTypes.UKR;
    }

    public String getPropertieValue() {
        return word;
    }

    public WordsPropertyTypes getType() {
        return type;
    }
}
