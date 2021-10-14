package by.gsu.epamlab.controllers;
import by.gsu.epamlab.constants.ConstantsJSP;
import by.gsu.epamlab.factories.TaskFactory;
import by.gsu.epamlab.factories.UserFactory;


import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.util.ResourceBundle;

@WebServlet(
        urlPatterns = ConstantsJSP.CONTEXT_PATH,
        initParams = {@WebInitParam(name = ConstantsJSP.PROPERTIES_NAME_KEY, value = ConstantsJSP.PROPERTIES_NAME_PATH)},
        loadOnStartup = 1
)
public class MainController extends HttpServlet {
    private static final long serialVersionUID = 6L;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        String propsName = config.getInitParameter(ConstantsJSP.PROPERTIES_NAME_KEY);
        ResourceBundle rb = ResourceBundle.getBundle(propsName);
        UserFactory.init(rb);
        TaskFactory.init(rb);
    }
}
