package enotes.entity.user.service;

import enotes.entity.user.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    void save(User user);

    long delete(User user);

    long delete(long id);

    Optional<User> get(long id);

    void update(User user);

    List<User> getAllUsers();
}