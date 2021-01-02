package enotes.data.user;

import java.util.List;
import java.util.Optional;

public interface UserService {

    void save(User user);

    long delete(User user);

    long delete(long id);

    Optional<User> get(long id);

    void update(User user);

    List<User> getAllUsers();

    void defaultSave(User user);

    Optional<User> getByEmail(String email);
}
