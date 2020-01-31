package enotes.user.service;

import enotes.user.User;
import enotes.user.dao.UserDao;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User save(User user) {
        if (user == null) {
            LOGGER.error("Passed user is null, user creation denied.");
            return null;
        }

        userDao.add(user);
        return user;
    }

    @Override
    public long delete(User user) {
        if (user == null) {
            LOGGER.error("Passed user is null, user deletion denied.");
            return -1;
        }

        Long userId = user.getId();
        if (userId == null || userId < 1) {
            LOGGER.error("Can't delete user with id equals null or less than 1. Id = {}", userId);
            return -1;
        }

        userDao.delete(userId);
        return userId;
    }

    @Override
    public long delete(long id) {
        if (id < 1) {
            LOGGER.error("Passed user id is less than 1, user deletion denied.");
            return -1;
        }

        userDao.delete(id);
        return id;
    }

    @Override
    public User get(User user) {
        if (user == null) {
            LOGGER.error("Passed user is null, user getting denied.");
            return null;
        }

        Long userId = user.getId();
        if (userId == null || userId < 1) {
            LOGGER.error("Can't find user with id equals null or less than 1. Id = {}", userId);
            return null;
        }

        return userDao.find(userId);
    }

    @Override
    public User get(long id) {
        if (id < 1) {
            LOGGER.error("Can't find user with id less than 1. Id = {}", id);
            return null;
        }

        return userDao.find(id);
    }

    @Override
    public User update(User user) {
        if (user == null) {
            LOGGER.error("Passed user is null, user updating denied.");
            return null;
        }

        Long userId = user.getId();
        if (userId == null || userId < 1) {
            LOGGER.error("Can't find user with id equals null or less than 1, updating denied. Id = {}", userId);
            return null;
        }

        userDao.update(user);
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.findAll();
    }
}
