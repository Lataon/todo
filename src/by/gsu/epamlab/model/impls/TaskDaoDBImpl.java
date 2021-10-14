package by.gsu.epamlab.model.impls;

import by.gsu.epamlab.constants.ConstantsJSP;
import by.gsu.epamlab.constants.ConstantsSql;
import by.gsu.epamlab.interfaces.OperationExecutor;
import by.gsu.epamlab.model.enums.Filter;
import by.gsu.epamlab.interfaces.TaskDao;
import by.gsu.epamlab.model.enums.Operation;
import by.gsu.epamlab.exceptions.DaoException;
import by.gsu.epamlab.model.bean.Task;
import by.gsu.epamlab.model.sevices.PoolConnection;
import by.gsu.epamlab.model.bean.TransferObject;

import java.io.InputStream;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static by.gsu.epamlab.constants.ConstantsSql.*;

public class TaskDaoDBImpl implements TaskDao {
    private static final Logger LOGGER = Logger.getLogger(TaskDaoDBImpl.class.getName());

    private static final Map<Filter, String> sqls = new EnumMap<>(Filter.class);

    static {
        sqls.put(Filter.TODAY, GET_TODAY_TASKS);
        sqls.put(Filter.TOMORROW, GET_TOMORROW_TASKS);
        sqls.put(Filter.SOMEDAY, GET_SOMEDAY_TASKS);
        sqls.put(Filter.FIXED, GET_FIXED_TASKS);
        sqls.put(Filter.BIN, GET_REMOVED_TASKS);
    }

    @Override
    public OperationExecutor getExecutor() {
        return new OperationDBExecutor();
    }

    @Override
    public List<Task> getTasks(int userId, Filter filter) throws DaoException {
        final int PS_PARAMETER = 1;
        final int ID_NUMBER = 1;
        final int DESCRIPTION_NUMBER = 2;
        final int DATE_NUMBER = 3;
        final int FILENAME_NUMBER = 4;

        try (Connection cn = PoolConnection.getConnection();
             PreparedStatement ps = cn.prepareStatement(sqls.get(filter))) {
            ps.setInt(PS_PARAMETER, userId);
            List<Task> tasks = new ArrayList<>();
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    tasks.add(new Task(
                            rs.getInt(ID_NUMBER),
                            rs.getString(DESCRIPTION_NUMBER),
                            rs.getDate(DATE_NUMBER).toLocalDate(),
                            rs.getString(FILENAME_NUMBER)
                    ));
                }
                return tasks;
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
            throw new DaoException(ConstantsJSP.GET_TASKS_ERROR, e);
        }
    }

    @Override
    public void executeOperation(int userId, Operation operation, TransferObject transferObject) throws DaoException {
        operation.execute(userId, transferObject);
    }

    @Override
    public Optional<Task> getTaskById(int idTask) throws DaoException {
        final int taskIdIndex = 1;
        try (Connection connection = PoolConnection.getConnection();
             PreparedStatement selectTaskPs = connection.prepareStatement(ConstantsSql.SELECT_TASK_BY_ID_QUERY)) {
            selectTaskPs.setInt(taskIdIndex, idTask);
            Task task = null;
            final int fileNameIndex = 1, fileIndex = 2;
            try (ResultSet rs = selectTaskPs.executeQuery()) {
                if (rs.next()) {
                    task = new Task(
                            rs.getString(fileNameIndex),
                            rs.getBinaryStream(fileIndex)
                    );
                }
                return Optional.ofNullable(task);
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, e.toString(), e);
                throw new DaoException(e);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
            throw new DaoException(e);
        }
    }

    @Override
    public void addFile(int idTask, InputStream fileInputStream, String filename) throws DaoException {
        final int fileNameParamIndex = 1, fileInputStreamIndex = 2, taskIdParamIndex = 3;
        try (Connection connection = PoolConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(ConstantsSql.ADD_FILE_QUERY)) {
            ps.setString(fileNameParamIndex, filename);
            ps.setBlob(fileInputStreamIndex, fileInputStream);
            ps.setInt(taskIdParamIndex, idTask);
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
            throw new DaoException(e);
        }
    }

    @Override
    public void deleteFile(int idTask) throws DaoException {
        final int taskIdParamIndex = 1;
        try (Connection connection = PoolConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(ConstantsSql.DELETE_FILE_QUERY)) {
            ps.setInt(taskIdParamIndex, idTask);
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
            throw new DaoException(e);
        }
    }
}
