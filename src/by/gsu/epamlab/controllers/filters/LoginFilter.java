package by.gsu.epamlab.controllers.filters;

import by.gsu.epamlab.constants.ConstantsJSP;
import by.gsu.epamlab.model.bean.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter({
        ConstantsJSP.TASKS_PATH,
        ConstantsJSP.DELETE_PATH,
        ConstantsJSP.DOWNLOAD_PATH,
        ConstantsJSP.UPLOAD_PATH,
        ConstantsJSP.MAIN_HTML_PATH
})
public class LoginFilter implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession();
        User user = (User) session.getAttribute(ConstantsJSP.USER_KEY);
        if (user == null) {
            session.invalidate();
            HttpServletResponse httpResponse =
                    (HttpServletResponse) response;
            httpResponse.sendRedirect(httpRequest.getContextPath() + ConstantsJSP.PAGE_LOGIN + ConstantsJSP.SESSION_INVALIDATED);
            return;
        }
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}

