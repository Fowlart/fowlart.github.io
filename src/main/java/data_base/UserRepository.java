package data_base;

import entities.User;

public interface UserRepository {

    Iterable<User> findAll();

    User findById(long id);

    Long save(User user);
}
