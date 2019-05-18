package data_base;

import entities.User;
import entities.WordTranslate;

import java.util.List;

public interface UserRepository {

    Iterable<User> findAll();

    User findById(User user);

    User save(User user);

    public List<WordTranslate> getWords(User user);

}
