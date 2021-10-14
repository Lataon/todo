package by.gsu.epamlab.controllers.filters;

import by.gsu.epamlab.constants.ConstantsJSP;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter({
		ConstantsJSP.CONTEXT_PATH,
		ConstantsJSP.TASKS_PATH,
		ConstantsJSP.LOGIN_PATH,
		ConstantsJSP.LOGOUT_PATH,
		ConstantsJSP.REG_PATH,
		ConstantsJSP.ABSTRACT_CONTROLLER_PATH,
		ConstantsJSP.DELETE_PATH,
		ConstantsJSP.DOWNLOAD_PATH,
		ConstantsJSP.UPLOAD_PATH,
		ConstantsJSP.MAIN_HTML_PATH,
		ConstantsJSP.PAGE_REG,
		ConstantsJSP.PAGE_LOGIN,
		ConstantsJSP.PAGE_FOOTER
})
public class TypedUrlFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response; 
		String refferer = httpRequest.getHeader(ConstantsJSP.REFFERER_KEY);
		if (refferer == null) {
			httpResponse.sendRedirect(httpRequest.getContextPath());
			return;
		}
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

	public void destroy() {
	}

}
