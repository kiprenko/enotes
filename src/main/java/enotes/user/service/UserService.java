package enotes.user.service;

import enotes.user.User;

import java.util.List;

public interface UserService {

    User create(User user);

    long delete(User user);

    long delete(long id);

    User get(User user);

    User get(long id);

    User update(User user);

    List<User> getAllUsers();
}
