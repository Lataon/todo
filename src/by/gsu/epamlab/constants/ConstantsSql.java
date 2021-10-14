package by.gsu.epamlab.constants;

public class ConstantsSql {
    //USER DAO queries
    public static final String SELECT_USER = "SELECT userId, userName, password FROM USERS WHERE userName=? AND password=?";
    public static final String ADD_USER = "INSERT INTO USERS (userName, password) VALUES (?, ?)";

    //TASK DAO queries
    public static final String SELECT_TASKS = "SELECT taskId, description, dateTask, fileName, file FROM TASKS WHERE userId=?";

    public static final String GET_TODAY_TASKS = SELECT_TASKS + " and dateTask <= CURRENT_DATE() and (NOT isFixed AND NOT isRemoved)";
    public static final String GET_TOMORROW_TASKS = SELECT_TASKS + " and dateTask = CURDATE() + INTERVAL 1 DAY and (NOT isFixed AND NOT isRemoved)";
    //    public static final String GET_SOMEDAY_TASKS = SELECT_TASKS + " and (NOT isFixed AND NOT isRemoved)";
    public static final String GET_SOMEDAY_TASKS = SELECT_TASKS + " and dateTask > CURRENT_DATE() + INTERVAL 1 DAY and (NOT isFixed AND NOT isRemoved)";
    public static final String GET_FIXED_TASKS = SELECT_TASKS + " and isFixed and NOT isRemoved";
    public static final String GET_REMOVED_TASKS = SELECT_TASKS + " and isRemoved";

    public static final String ADD_TASK = "INSERT INTO TASKS (userId, description, dateTask) VALUES (?, ?, ?)";
    public static final String REMOVE_TASK = "UPDATE TASKS SET isRemoved = true WHERE taskId=?";
    public static final String FIX_TASK = "UPDATE TASKS SET isFixed = true WHERE taskId=?";
    public static final String DELETE_TASK = "DELETE FROM TASKS WHERE taskId=?";
    public static final String REPAIR_TASK = "UPDATE TASKS SET isRemoved = false WHERE taskId=?";
    public static final String CLEAR_TASKS = "DELETE FROM TASKS WHERE isRemoved and userId = ?";
    public static final String SELECT_TASK_BY_ID_QUERY = "select fileName, file from tasks where taskId=?";

    public static final String ADD_FILE_QUERY = "UPDATE tasks SET fileName=?, file=? WHERE taskId=?";
    public static final String DELETE_FILE_QUERY = "UPDATE tasks SET fileName=null, file=null WHERE taskId=?";

}
