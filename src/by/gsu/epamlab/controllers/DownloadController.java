package by.gsu.epamlab.controllers;

import by.gsu.epamlab.constants.ConstantsJSP;
import by.gsu.epamlab.exceptions.DaoException;
import by.gsu.epamlab.factories.TaskFactory;
import by.gsu.epamlab.interfaces.TaskDao;
import by.gsu.epamlab.model.bean.Task;
import by.gsu.epamlab.utils.Utilities;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Optional;

@WebServlet(ConstantsJSP.DOWNLOAD_PATH)
public class DownloadController extends AbstractController {
    private static final long serialVersionUID = 3L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String taskIdStr = request.getParameter(ConstantsJSP.TASKS_KEY);
        int taskId = Integer.parseInt(taskIdStr);
        TaskDao taskDao = TaskFactory.getTaskDao();
        try {
            Optional<Task> optTask = taskDao.getTaskById(taskId);
            Task task = optTask.orElseThrow(DaoException::new);
            String fileName = task.getFileName();
            final String headerName = "Content-disposition";
            final String headerValue = "attachment; filename=" + fileName;
            response.setHeader(headerName, headerValue);
            InputStream fileInputStream = task.getFileInputStream();
            OutputStream fileOutputStream = response.getOutputStream();
            Utilities.downloadFileViaBrowser(fileInputStream, fileOutputStream);
        } catch (DaoException e) {
            forwardWithDao(request, response, e);
        }
    }
}
