package enotes.entity.user.service;

import enotes.entity.user.User;
import enotes.entity.user.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void save(User user) {
        if (user == null) {
            throw new IllegalArgumentException();
        }

        userRepository.save(user);
    }

    @Override
    public long delete(User user) {
        if (user == null) {
            throw new IllegalArgumentException();
        }

        Long userId = user.getId();
        if (userId == null || userId < 1) {
            throw new IllegalArgumentException(String.format("Can't delete user with id equals null or less than 1. Id = %d", userId));
        }

        userRepository.delete(user);
        return userId;
    }

    @Override
    public long delete(long id) {
        if (id < 1) {
            throw new IllegalArgumentException();
        }

        userRepository.deleteById(id);
        return id;
    }

    @Override
    public Optional<User> get(long id) {
        if (id < 1) {
            throw new IllegalArgumentException();
        }

        return userRepository.findById(id);
    }

    @Override
    public void update(User user) {
        if (user == null) {
            throw new IllegalArgumentException();
        }

        Long userId = user.getId();
        if (userId == null || userId < 1) {
            throw new IllegalArgumentException(String.format("Can't find user with id equals null or less than 1, updating denied. Id = %d", userId));
        }

        userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }
}
