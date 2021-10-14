package by.gsu.epamlab.factories;

import by.gsu.epamlab.constants.ConstantsJSP;
import by.gsu.epamlab.interfaces.TaskDao;
import by.gsu.epamlab.model.sevices.PoolConnection;
import by.gsu.epamlab.model.impls.TaskDaoDBImpl;

import java.util.ResourceBundle;

public class TaskFactory {
    private enum DaoImpls{
        DB {
            @Override
            TaskDao getImpl(ResourceBundle rb) {
                PoolConnection.init(
                        rb.getString(ConstantsJSP.DB_NAME_KEY),
                        rb.getString(ConstantsJSP.DB_USER_KEY),
                        rb.getString(ConstantsJSP.DB_PASSWORD_KEY)
                );
                return new TaskDaoDBImpl();
            }
        };
        abstract TaskDao getImpl(ResourceBundle rb);
    }

    private static TaskDao taskDao;

    public static void init(ResourceBundle rb) {
        taskDao = DaoImpls.valueOf(rb.getString(ConstantsJSP.FACTORY_TASKS_KEY).toUpperCase()).getImpl(rb);
    }

    public static TaskDao getTaskDao() {
        return taskDao;
    }
}
