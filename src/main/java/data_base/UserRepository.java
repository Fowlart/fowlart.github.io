package data_base;

import entities.User;

public interface UserRepository {

    Iterable<User> findAll();

    User findById(long id);

    User findByName(String name);

    Long save(User user);
}
