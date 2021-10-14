package by.gsu.epamlab.controllers;

import by.gsu.epamlab.constants.ConstantsJSP;
import by.gsu.epamlab.model.bean.User;
import by.gsu.epamlab.model.enums.Filter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(ConstantsJSP.ABSTRACT_CONTROLLER_PATH)
public class AbstractController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(AbstractController.class.getName());

    protected void forwardWithValidation(HttpServletRequest req, HttpServletResponse resp, String message, String location) throws ServletException, IOException {
        LOGGER.log(Level.SEVERE, message);
        req.setAttribute(ConstantsJSP.ERROR_VALIDATION, message);
        RequestDispatcher rd = getServletContext().getRequestDispatcher(location);
        rd.forward(req, resp);
    }

    protected void sendErrorPage(HttpServletResponse resp, Throwable e, String message, int var) throws IOException {
        LOGGER.log(Level.SEVERE, e.toString(), e);
        resp.sendError(var, message);
    }

    protected void forwardWithDao(HttpServletRequest req, HttpServletResponse resp, Throwable e) throws ServletException, IOException {
        LOGGER.log(Level.SEVERE, e.toString(), e);
        req.setAttribute(ConstantsJSP.ERROR_DAO, e.getMessage());
        RequestDispatcher rd = getServletContext().getRequestDispatcher(ConstantsJSP.PAGE_START);
        rd.forward(req, resp);
    }

    protected void redirectToPage(HttpServletRequest req, HttpServletResponse resp, String path) throws IOException {
      resp.sendRedirect(req.getContextPath() + path);
    }

    protected void redirectToMainPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String strFilter = request.getParameter(ConstantsJSP.FILTER_KEY);
        Filter filter = strFilter == null ? Filter.TODAY : Filter.getByStr(strFilter);
        User user = getUserFromSession(request);
        String mainPageUrl = "/main.html?" +
                "filter=" +
                filter.getParamName() +
                "&" +
                "login=" + user.getName();
        redirectToPage(request, response, mainPageUrl);
    }

    protected User getUserFromSession(HttpServletRequest request){
        HttpSession session = request.getSession();
        return (User) session.getAttribute(ConstantsJSP.USER_KEY);
    }
}

