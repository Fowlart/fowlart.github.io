package data_base;

import entities.User;
import entities.WordTranslate;

import java.util.List;

public interface UserRepository {

    Iterable<User> findAll();

    User findById(User user);

    User save(User user);

    boolean updateWordsList(User user, List<WordTranslate> new_words_list);

    public List<WordTranslate> getWords(User user);

}
