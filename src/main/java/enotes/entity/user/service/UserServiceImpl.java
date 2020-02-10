package enotes.entity.user.service;

import enotes.entity.user.User;
import enotes.entity.user.dao.UserDao;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void save(User user) {
        if (user == null) {
            throw new IllegalArgumentException();
        }

        userDao.add(user);
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

        userDao.delete(userId);
        return userId;
    }

    @Override
    public long delete(long id) {
        if (id < 1) {
            throw new IllegalArgumentException();
        }

        userDao.delete(id);
        return id;
    }

    @Override
    public Optional<User> get(User user) {
        if (user == null) {
            throw new IllegalArgumentException();
        }

        Long userId = user.getId();
        if (userId == null || userId < 1) {
            throw new IllegalArgumentException(String.format("Can't find user with id equals null or less than 1. Id = %d", userId));
        }

        return userDao.find(userId);
    }

    @Override
    public Optional<User> get(long id) {
        if (id < 1) {
            throw new IllegalArgumentException();
        }

        return userDao.find(id);
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

        userDao.update(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.findAll();
    }
}
