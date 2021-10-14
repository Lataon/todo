package by.gsu.epamlab.constants;

public class ConstantsJSP {
    public static final String DELIMETER = ";";

    //header
    public static final String REFFERER_KEY = "referer";

    //properties
    public static final String PROPERTIES_NAME_KEY = "propsName";
    public static final String PROPERTIES_NAME_PATH = "resources.init";
    public static final String DB_NAME_KEY = "db.name";
    public static final String DB_USER_KEY = "db.user";
    public static final String DB_PASSWORD_KEY = "db.password";
    public static final String FACTORY_USER_KEY = "factory.user";
    public static final String FACTORY_TASKS_KEY = "factory.tasks";

    //parameters
    public static final String USER_KEY = "user";
    public static final String USER_NAME_KEY = "uname";
    public static final String PASSWORD_KEY = "psw";
    public static final String FILTER_KEY = "filter";
    public static final String DESCRIPTION_KEY = "text";
    public static final String CALENDAR_KEY = "date";
    public static final String TASKS_KEY = "idTask";
    public static final String OPERATION_KEY = "operation";
    public static final String FILE_TO_UPLOAD_NAME = "fileToUpload";

    //attributes
    public static final String ERROR_VALIDATION = "errorValidation";
    public static final String ERROR_DAO = "errorDAO";

    //pages
    public static final String PAGE_LOGIN = "/login.jsp";
    public static final String PAGE_REG = "/reg.jsp";
    public static final String REGISTERED_MSG_PARAMS = "?reg=yes";
    public static final String PAGE_START = "/index.jsp";
    public static final String PAGE_FOOTER = "/footer.jsp";

    //paths
    public static final String TASKS_PATH = "/tasks";
    public static final String CONTEXT_PATH = "/webCoop";
    public static final String LOGIN_PATH = "/login";
    public static final String LOGOUT_PATH = "/logout";
    public static final String REG_PATH = "/reg";
    public static final String ABSTRACT_CONTROLLER_PATH = "/AbstractController";
    public static final String DELETE_PATH = "/delete";
    public static final String DOWNLOAD_PATH = "/download";
    public static final String UPLOAD_PATH = "/upload";
    public static final String OPERATIONS_PATH = "/operations";
    public static final String MAIN_HTML_PATH = "/main.html";


    //Exceptions messages
    public static final String LOGIN_VALIDATION_ERROR = "Wrong user name or password. Repeat please authorization or register";
    public static final String REG_VALIDATION_ERROR = "Login with this name has already registered";
    public static final String GET_TASKS_ERROR = "problem with getting tasks";
    public static final String GET_USER_ERROR = "problem with getting user";
    public static final String ADD_USER_ERROR = "problem with adding user";
    public static final String ADD_TASK_ERROR = "problem with adding task";
    public static final String MODIFY_TASK_ERROR = "problem with modify task";
    public static final String CLEAR_TASKS_ERROR = "problem with clear tasks";
    public static final String NO_FILTER_ERROR = "no filter parameter";
    public static final String NO_OPERATION_ERROR = "no operation parameter";
    public static final String NO_FIELDS_ERROR = "no operation parameters: description=%s, strDate=%s";
    public static final String DATE_FORMAT_ERROR = "no valid date format ";
    public static final String EMPTY_TASK_ARRAY_ERROR = "no any tasks id was chosen";
    public static final String TASK_ARRAY__NUMBER_ERROR = "task ids have not number parameters";
    public static final String SESSION_INVALIDATED = "?errorSession=yes";
}
