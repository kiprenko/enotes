package enotes.user.dao;

import enotes.user.User;

import java.util.List;

public class JdbcUserDao implements UserDao {

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User find(Long id) {
        return null;
    }

    @Override
    public boolean add(User note) {
        return false;
    }

    @Override
    public boolean update(User note) {
        return false;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }
}
