package by.gsu.epamlab.controllers;

import by.gsu.epamlab.constants.ConstantsJSP;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(ConstantsJSP.LOGOUT_PATH)
public class LogoutController extends HttpServlet {
    private static final long serialVersionUID = 5L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null) {
            req.logout();
            session.invalidate();
        }
        resp.sendRedirect(req.getContextPath());
    }
}
