package data_base;

import entities.User;
import entities.WordTranslate;

import java.util.List;

public interface UserRepository {

    Iterable<User> findAll();

    User findById(Long id);

    User save(User user, List<WordTranslate> list);

    public List<WordTranslate> getWords(Long user_id);

}
