package by.gsu.epamlab.model.impls;

import by.gsu.epamlab.constants.ConstantsJSP;
import by.gsu.epamlab.constants.ConstantsSql;
import by.gsu.epamlab.exceptions.DaoException;
import by.gsu.epamlab.interfaces.OperationExecutor;
import by.gsu.epamlab.model.enums.Operation;
import by.gsu.epamlab.model.sevices.PoolConnection;
import by.gsu.epamlab.model.bean.TransferObject;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.EnumMap;
import java.util.Map;

public class OperationDBExecutor implements OperationExecutor {
    private final static Map<Operation, String> sqls = new EnumMap<>(Operation.class);

    static {
        sqls.put(Operation.ADD, ConstantsSql.ADD_TASK);
        sqls.put(Operation.REMOVE, ConstantsSql.REMOVE_TASK);
        sqls.put(Operation.FIX, ConstantsSql.FIX_TASK);
        sqls.put(Operation.DELETE, ConstantsSql.DELETE_TASK);
        sqls.put(Operation.CLEAR, ConstantsSql.CLEAR_TASKS);
        sqls.put(Operation.REPAIR, ConstantsSql.REPAIR_TASK);
    }

    public void add (int userId, TransferObject transferObject, Operation operation) throws DaoException {
        final int PS_USER_ID_PARAM = 1;
        final int PS_DESCR_PARAM = 2;
        final int PS_DATE_PARAM = 3;

        try (Connection cn = PoolConnection.getConnection();
             PreparedStatement ps = cn.prepareStatement(sqls.get(operation))) {
                String description = transferObject.getDescription();
                Date date = transferObject.getDate();

                ps.setInt(PS_USER_ID_PARAM, userId);
                ps.setString(PS_DESCR_PARAM, description);
                ps.setDate(PS_DATE_PARAM, date);
                ps.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException(ConstantsJSP.ADD_TASK_ERROR, e);
        }
    }

    public void modify (int userId, TransferObject transferObject, Operation operation) throws DaoException {
        final int PS_TASK_ID_PARAM = 1;

        try (Connection cn = PoolConnection.getConnection();
             PreparedStatement ps = cn.prepareStatement(sqls.get(operation))) {

            int[] taskIdArr = transferObject.getTaskIds();
            for (int taskId : taskIdArr) {
                ps.setInt(PS_TASK_ID_PARAM, taskId);
                ps.addBatch();
            }
            ps.executeBatch();
        } catch (SQLException e) {
            throw new DaoException(ConstantsJSP.MODIFY_TASK_ERROR, e);
        }
    }

    public void clearTasksOperation(int userId, TransferObject transferObject, Operation operation) throws DaoException {
        final int PS_USER_ID_PARAM = 1;

        try (Connection cn = PoolConnection.getConnection();
             PreparedStatement ps = cn.prepareStatement(sqls.get(operation))) {
            ps.setInt(PS_USER_ID_PARAM, userId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(ConstantsJSP.CLEAR_TASKS_ERROR, e);
        }
    }

}
