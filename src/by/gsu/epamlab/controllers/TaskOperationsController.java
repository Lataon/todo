package by.gsu.epamlab.controllers;

import by.gsu.epamlab.constants.ConstantsJSP;
import by.gsu.epamlab.interfaces.TaskDao;
import by.gsu.epamlab.model.enums.Operation;
import by.gsu.epamlab.exceptions.DaoException;
import by.gsu.epamlab.exceptions.ValidateRunTimeException;
import by.gsu.epamlab.factories.TaskFactory;
import by.gsu.epamlab.model.bean.User;
import by.gsu.epamlab.model.bean.TransferObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

@WebServlet(ConstantsJSP.OPERATIONS_PATH)
public class TaskOperationsController extends AbstractController {
    private static final long serialVersionUID = 8L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String[]> params = req.getParameterMap();
        String strOperation = req.getParameter(ConstantsJSP.OPERATION_KEY);
        try {
            if (strOperation == null) throw new ValidateRunTimeException(ConstantsJSP.NO_OPERATION_ERROR);
            Operation operation = Operation.getOperationByStr(strOperation);
            TransferObject transferObject = operation.checkParameters(params);

            HttpSession session = req.getSession();
            User user = (User) session.getAttribute(ConstantsJSP.USER_KEY);

            TaskDao taskDao = TaskFactory.getTaskDao();
            taskDao.executeOperation(user.getId(), operation, transferObject);
            redirectToMainPage(req, resp);
        }  catch (ValidateRunTimeException e) {
            sendErrorPage(resp, e, e.getMessage(), HttpServletResponse.SC_BAD_REQUEST);
        }  catch (DaoException e) {
            forwardWithDao(req, resp, e);
        }
    }
}
