package by.gsu.epamlab.controllers;

import by.gsu.epamlab.constants.ConstantsJSP;
import by.gsu.epamlab.interfaces.TaskDao;
import by.gsu.epamlab.model.enums.Filter;
import by.gsu.epamlab.exceptions.DaoException;
import by.gsu.epamlab.exceptions.ValidateRunTimeException;
import by.gsu.epamlab.factories.TaskFactory;
import by.gsu.epamlab.model.bean.Task;
import by.gsu.epamlab.model.bean.User;
import by.gsu.epamlab.utils.Utilities;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


@WebServlet(
        urlPatterns = ConstantsJSP.TASKS_PATH
)
public class TasksController extends AbstractController {
    private static final long serialVersionUID = 9L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String strFilter = req.getParameter(ConstantsJSP.FILTER_KEY);
        try {
            if (strFilter==null) throw new ValidateRunTimeException(ConstantsJSP.NO_FILTER_ERROR); //check section from login
            Filter filter = Filter.getByStr(strFilter);
            HttpSession session = req.getSession();
            User user = (User) session.getAttribute(ConstantsJSP.USER_KEY);
            TaskDao taskDao = TaskFactory.getTaskDao();
            List<Task> tasks = taskDao.getTasks(user.getId(), filter);
            String jsonTasks = Utilities.getJsonTasks(tasks);
            resp.getWriter().write(jsonTasks);
        } catch (ValidateRunTimeException e) {
            sendErrorPage(resp, e, e.getMessage(), HttpServletResponse.SC_BAD_REQUEST);
        } catch (DaoException e) {
            sendErrorPage(resp, e, e.getMessage(), HttpServletResponse.SC_SERVICE_UNAVAILABLE);
        }
    }
}
