package dao;

import java.util.List;

import domain.User;

public interface UserDao extends Dao<User> {
    List<User> readAll() throws DaoException;

    User readByLogin(String login) throws DaoException;

    User readByLoginAndPassword(String login, String password) throws DaoException;
}
