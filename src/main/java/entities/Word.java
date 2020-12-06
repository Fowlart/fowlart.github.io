package entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.List;

@Document(collection = "words")
public class Word {

    @Id
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Size(min = 2, message = "English word must be at least 2 characters long")
    private String engword;

    @Size(min = 2, message = "Ukraine word be at least 2 characters long")
    private String ukrword;

    @Min(value = 0, message = "Not in bound. Must be between 0 and 30")
    @Max(value = 30, message = "Not in bound. Must be between 0 and 30")
    private Integer points;

    private List<String> users;

    public String getEngword() {
        return engword;
    }

    public void setEngword(String engword) {
        this.engword = engword;
    }

    public String getUkrword() {
        return ukrword;
    }

    public void setUkrword(String ukrword) {
        this.ukrword = ukrword;
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
        return "WordDTO{" +
                "id='" + id + '\'' +
                ", engword='" + engword + '\'' +
                ", ukrword='" + ukrword + '\'' +
                ", points=" + points +
                ", users=" + users +
                '}';
    }
}
