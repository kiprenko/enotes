package enotes.user.service;

import enotes.user.User;
import enotes.user.dao.UserDao;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static enotes.utils.validation.UserValidationUtils.isValidUser;

@Log4j2
@Service
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User create(User user) {
        if (user == null) {
            LOGGER.error("Passed user is null, user creation denied.");
            return null;
        } else if(!isValidUser(user)) {
            LOGGER.error("Passed user is invalid, user creation denied.");
            return null;
        }

        if (userDao.add(user)) {
            return user;
        }

        LOGGER.error("User creation was unsuccessful.");
        return null;
    }

    @Override
    public long delete(User user) {
        return 0;
    }

    @Override
    public long delete(long id) {
        return 0;
    }

    @Override
    public User get(User user) {
        return null;
    }

    @Override
    public User get(long id) {
        return null;
    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }
}
