package by.gsu.epamlab.controllers;

import by.gsu.epamlab.constants.ConstantsJSP;
import by.gsu.epamlab.exceptions.DaoException;
import by.gsu.epamlab.factories.TaskFactory;
import by.gsu.epamlab.interfaces.TaskDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(ConstantsJSP.DELETE_PATH)
public class DeleteFileController extends AbstractController {
    private static final long serialVersionUID = 2L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idTaskString = request.getParameter(ConstantsJSP.TASKS_KEY);
        int taskId = Integer.parseInt(idTaskString);
        TaskDao taskDao = TaskFactory.getTaskDao();
        try {
            taskDao.deleteFile(taskId);
            redirectToMainPage(request, response);
        } catch (DaoException e) {
            forwardWithDao(request, response, e);
        }
    }
}
