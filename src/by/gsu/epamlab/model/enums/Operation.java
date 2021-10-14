package by.gsu.epamlab.model.enums;

import by.gsu.epamlab.factories.TaskFactory;
import by.gsu.epamlab.interfaces.OperationExecutor;
import by.gsu.epamlab.model.impls.ParametersChecker;
import by.gsu.epamlab.exceptions.DaoException;
import by.gsu.epamlab.exceptions.ValidateRunTimeException;
import by.gsu.epamlab.model.bean.TransferObject;

import java.util.Map;

public enum Operation {
    ADD{
        @Override
        public TransferObject checkParameters(Map<String, String[]> params) {
            return ParametersChecker.checkFieldsTask(params);
        }

        @Override
        public void execute(int userId, TransferObject transferObject) throws DaoException {
            operationExecutor.add(userId, transferObject, this);
        }
    },
    REMOVE{
        @Override
        public TransferObject checkParameters(Map<String, String[]> params) {
            return ParametersChecker.checkTasksId(params);
        }
        @Override
        public void execute(int userId, TransferObject transferObject) throws DaoException {
            operationExecutor.modify(userId, transferObject, this);
        }
    },
    FIX{
        @Override
        public TransferObject checkParameters(Map<String, String[]> params) {
            return ParametersChecker.checkTasksId(params);
        }
        @Override
        public void execute(int userId, TransferObject transferObject) throws DaoException {
            operationExecutor.modify(userId, transferObject, this);
        }
    },
    DELETE{
        @Override
        public TransferObject checkParameters(Map<String, String[]> params) {
            return ParametersChecker.checkTasksId(params);
        }
        @Override
        public void execute(int userId, TransferObject transferObject) throws DaoException {
            operationExecutor.modify(userId, transferObject, this);
        }
    },
    CLEAR{
        @Override
        public TransferObject checkParameters(Map<String, String[]> params) {
            return new TransferObject();
        }
        @Override
        public void execute(int userId, TransferObject transferObject) throws DaoException {
            operationExecutor.clearTasksOperation(userId, transferObject, this);
        }
    },
    REPAIR{
        @Override
        public TransferObject checkParameters(Map<String, String[]> params) {
            return ParametersChecker.checkTasksId(params);
        }
        @Override
        public void execute(int userId, TransferObject transferObject) throws DaoException {
            operationExecutor.modify(userId, transferObject, this);
        }
    };

    public abstract TransferObject checkParameters(Map<String, String[]> params);
    public abstract void execute(int userId, TransferObject transferObject) throws DaoException;

    private final static OperationExecutor operationExecutor = TaskFactory.getTaskDao().getExecutor();

    public static Operation getOperationByStr(String strOperation) {
        try {
            return valueOf(strOperation.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new ValidateRunTimeException("no valid operation name " + strOperation);
        }
    }
}
