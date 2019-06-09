package entities;

import lombok.Data;

import java.util.List;

@Data
public class User {

    // data from database
    private long id;
    private String name;
    private String password;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}