package by.gsu.epamlab.interfaces;

import by.gsu.epamlab.model.enums.Filter;
import by.gsu.epamlab.model.enums.Operation;
import by.gsu.epamlab.exceptions.DaoException;
import by.gsu.epamlab.model.bean.Task;
import by.gsu.epamlab.model.bean.TransferObject;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;

public interface TaskDao {
    OperationExecutor getExecutor();
    List<Task> getTasks(int userId, Filter filter) throws DaoException;

    void executeOperation(int userId, Operation operation, TransferObject transferObject) throws DaoException;

    Optional<Task> getTaskById(int idTask) throws DaoException;

    void addFile(int idTask, InputStream inputStream, String fileName) throws DaoException;

    void deleteFile(int idTask) throws DaoException;
}
