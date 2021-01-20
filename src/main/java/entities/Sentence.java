package entities;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "words")
public class Sentence {

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String sentence;

    private String fragment;

    private Integer points;

    private List<String> users;

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

    public String getFragment() {
        return fragment;
    }

    public void setFragment(String fragment) {
        this.fragment = fragment;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public List<String> getUsers() {
        return users;
    }

    public void setUsers(List<String> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Sentence{" +
                "id='" + id + '\'' +
                ", engword='" + sentence + '\'' +
                ", ukrword='" + fragment + '\'' +
                ", points=" + points +
                ", users=" + users +
                '}';
    }
}
