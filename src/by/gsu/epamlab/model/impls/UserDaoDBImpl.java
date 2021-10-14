package by.gsu.epamlab.model.impls;

import by.gsu.epamlab.constants.ConstantsJSP;
import by.gsu.epamlab.constants.ConstantsSql;
import by.gsu.epamlab.interfaces.UserDao;
import by.gsu.epamlab.exceptions.DaoException;
import by.gsu.epamlab.model.bean.User;
import by.gsu.epamlab.model.sevices.PoolConnection;

import java.sql.*;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDaoDBImpl implements UserDao {
    private static final Logger LOGGER = Logger.getLogger(UserDaoDBImpl.class.getName());

    @Override
    public Optional<User> getUser(String login, String password) throws DaoException {
        final int PS_LOGIN_PARAM = 1;
        final int PS_PSW_PARAM = 2;
        final int ID_NUMBER = 1;
        final int LOGIN_NUMBER = 2;
        final int PSW_NUMBER = 3;

        try (
                Connection connection = PoolConnection.getConnection();
                PreparedStatement stUser = connection.prepareStatement(ConstantsSql.SELECT_USER);
        ) {
            stUser.setString(PS_LOGIN_PARAM, login);
            stUser.setString(PS_PSW_PARAM, password);
            Optional<User> optUser = Optional.empty();
            try (ResultSet rs = stUser.executeQuery()) {
                while (rs.next()) {
                    optUser = Optional.of(
                            new User(rs.getInt(ID_NUMBER),
                                    rs.getString(LOGIN_NUMBER),
                                    rs.getString(PSW_NUMBER)));
                }
                return optUser;
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
            throw new DaoException(ConstantsJSP.GET_USER_ERROR, e);
        }
    }

    @Override
    public Optional<User> addAndGetUser(String login, String password) throws DaoException {
        final int PS_LOGIN_PARAM = 1;
        final int PS_PSW_PARAM = 2;

        try (
                Connection connection = PoolConnection.getConnection();
                PreparedStatement stUser = connection.prepareStatement(ConstantsSql.ADD_USER);
        ) {
            stUser.setString(PS_LOGIN_PARAM, login);
            stUser.setString(PS_PSW_PARAM, password);
            Optional<User> optUser = Optional.of(new User(login, password));
            synchronized (this) {
                if (getUser(login, password).isPresent()) {
                    optUser = Optional.empty();
                } else {
                    stUser.executeUpdate();
                }
            }
            return optUser;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
            throw new DaoException(ConstantsJSP.ADD_USER_ERROR, e);
        }
    }
}
