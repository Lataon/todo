package by.gsu.epamlab.controllers;

import by.gsu.epamlab.constants.ConstantsJSP;
import by.gsu.epamlab.interfaces.UserDao;
import by.gsu.epamlab.exceptions.DaoException;
import by.gsu.epamlab.exceptions.ValidationException;
import by.gsu.epamlab.factories.UserFactory;
import by.gsu.epamlab.model.bean.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet(ConstantsJSP.REG_PATH)
public class RegController extends AbstractController {
    private static final long serialVersionUID = 7L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            UserDao userDao = UserFactory.getUserDao();
            String name = req.getParameter(ConstantsJSP.USER_NAME_KEY);
            String password = req.getParameter(ConstantsJSP.PASSWORD_KEY);
            Optional<User> optUser =  userDao.addAndGetUser(name, password);
            User user = optUser.orElseThrow(() -> new ValidationException(ConstantsJSP.REG_VALIDATION_ERROR));
            req.getSession().setAttribute(ConstantsJSP.USER_KEY, user);
            redirectToPage(req, resp, ConstantsJSP.PAGE_LOGIN + ConstantsJSP.REGISTERED_MSG_PARAMS);
        } catch (ValidationException e) {
            forwardWithValidation(req, resp, e.getMessage(), ConstantsJSP.PAGE_REG);
        } catch (DaoException e) {
            forwardWithDao(req, resp, e);
        }
    }
}
