package dtos;

public class UserData {
    private String name;
    private String progress;
    private String wordsCount;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public String getWordsCount() {
        return wordsCount;
    }

    public void setWordsCount(String wordsCount) {
        this.wordsCount = wordsCount;
    }
}
