package by.gsu.epamlab.interfaces;

import by.gsu.epamlab.exceptions.DaoException;
import by.gsu.epamlab.model.bean.TransferObject;
import by.gsu.epamlab.model.enums.Operation;

public interface OperationExecutor {
    void add(int userId, TransferObject transferObject, Operation operation) throws DaoException;
    void modify(int userId, TransferObject transferObject, Operation operation) throws DaoException;
    void clearTasksOperation(int userId, TransferObject transferObject, Operation operation) throws DaoException;
}
