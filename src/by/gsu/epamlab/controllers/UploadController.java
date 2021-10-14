package by.gsu.epamlab.controllers;

import by.gsu.epamlab.constants.ConstantsJSP;
import by.gsu.epamlab.exceptions.DaoException;
import by.gsu.epamlab.factories.TaskFactory;
import by.gsu.epamlab.interfaces.TaskDao;
import by.gsu.epamlab.utils.Utilities;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;

@WebServlet(value = ConstantsJSP.UPLOAD_PATH)
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 15,
        maxFileSize = 1024 * 1024 * 15,
        maxRequestSize = 1024 * 1024 * 15)
public class UploadController extends AbstractController {
    private static final long serialVersionUID = 10L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String taskIdStr = req.getParameter(ConstantsJSP.TASKS_KEY);
        int taskId = Integer.parseInt(taskIdStr);
        Part filePart = req.getPart(ConstantsJSP.FILE_TO_UPLOAD_NAME);
        String fileName = Utilities.getSubmittedFileName(filePart); //get name + ext
        InputStream fileInputStream = filePart.getInputStream(); // input stream
        TaskDao taskDao = TaskFactory.getTaskDao();
        try {
            taskDao.addFile(taskId, fileInputStream, fileName);
            redirectToMainPage(req, resp);
        } catch (DaoException e) {
            forwardWithDao(req, resp, e);
        }
    }
}