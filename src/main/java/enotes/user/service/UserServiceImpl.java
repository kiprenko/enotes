package enotes.user.service;

import enotes.user.User;
import enotes.user.dao.UserDao;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void save(User user) {
        if (user == null) {
            LOGGER.error("Passed user is null, user creation denied.");
            throw new IllegalArgumentException();
        }

        userDao.add(user);
    }

    @Override
    public long delete(User user) {
        if (user == null) {
            LOGGER.error("Passed user is null, user deletion denied.");
            throw new IllegalArgumentException();
        }

        Long userId = user.getId();
        if (userId == null || userId < 1) {
            LOGGER.error("Can't delete user with id equals null or less than 1. Id = {}", userId);
            throw new IllegalArgumentException();
        }

        userDao.delete(userId);
        return userId;
    }

    @Override
    public long delete(long id) {
        if (id < 1) {
            LOGGER.error("Passed user id is less than 1, user deletion denied.");
            throw new IllegalArgumentException();
        }

        User user = User.builder().id(id).build();
        userDao.delete(user.getId());
        return user.getId();
    }

    @Override
    public Optional<User> get(User user) {
        if (user == null) {
            LOGGER.error("Passed user is null, user getting denied.");
            throw new IllegalArgumentException();
        }

        Long userId = user.getId();
        if (userId == null || userId < 1) {
            LOGGER.error("Can't find user with id equals null or less than 1. Id = {}", userId);
            throw new IllegalArgumentException();
        }

        return userDao.find(userId);
    }

    @Override
    public Optional<User> get(long id) {
        if (id < 1) {
            LOGGER.error("Can't find user with id less than 1. Id = {}", id);
            throw new IllegalArgumentException();
        }

        return userDao.find(id);
    }

    @Override
    public void update(User user) {
        if (user == null) {
            LOGGER.error("Passed user is null, user updating denied.");
            throw new IllegalArgumentException();
        }

        Long userId = user.getId();
        if (userId == null || userId < 1) {
            LOGGER.error("Can't find user with id equals null or less than 1, updating denied. Id = {}", userId);
            throw new IllegalArgumentException();
        }

        userDao.update(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.findAll();
    }
}
