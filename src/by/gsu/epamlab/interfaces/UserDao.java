package by.gsu.epamlab.interfaces;

import by.gsu.epamlab.exceptions.DaoException;
import by.gsu.epamlab.model.bean.User;

import java.util.Optional;

public interface UserDao {
    Optional<User> getUser(String login, String password) throws DaoException;

    Optional<User> addAndGetUser(String login, String password) throws DaoException;
}
