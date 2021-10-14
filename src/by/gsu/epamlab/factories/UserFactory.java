package by.gsu.epamlab.factories;

import by.gsu.epamlab.constants.ConstantsJSP;
import by.gsu.epamlab.interfaces.UserDao;
import by.gsu.epamlab.model.sevices.PoolConnection;
import by.gsu.epamlab.model.impls.UserDaoDBImpl;

import java.util.ResourceBundle;

public class UserFactory {
    private enum DaoImpls{
        DB {
            @Override
            UserDao getImpl(ResourceBundle rb) {
                PoolConnection.init(
                        rb.getString(ConstantsJSP.DB_NAME_KEY),
                        rb.getString(ConstantsJSP.DB_USER_KEY),
                        rb.getString(ConstantsJSP.DB_PASSWORD_KEY)
                );
                return new UserDaoDBImpl();
            }
        };
        abstract UserDao getImpl(ResourceBundle rb);
    }

    private static UserDao userDao;

    public static void init(ResourceBundle rb) {
        userDao = DaoImpls.valueOf(rb.getString(ConstantsJSP.FACTORY_USER_KEY).toUpperCase()).getImpl(rb);
    }

    public static UserDao getUserDao() {
        return userDao;
    }
}
