package by.gsu.epamlab.model.impls;

import by.gsu.epamlab.constants.ConstantsJSP;
import by.gsu.epamlab.exceptions.ValidateRunTimeException;
import by.gsu.epamlab.model.bean.TransferObject;

import java.sql.Date;
import java.util.Arrays;
import java.util.Map;

public class ParametersChecker {
    private static final int FIRST_POSITION = 0;

    public static TransferObject checkFieldsTask (Map<String, String[]> params) {
        String description = params.get(ConstantsJSP.DESCRIPTION_KEY)[FIRST_POSITION];
        String strDate = params.get(ConstantsJSP.CALENDAR_KEY)[FIRST_POSITION];
        if (description==null || strDate==null) {
            throw new ValidateRunTimeException(
                    String.format(ConstantsJSP.NO_FIELDS_ERROR, description, strDate)
            );
        }
        try {
            Date date = Date.valueOf(strDate);
            TransferObject to = new TransferObject();
            to.setDescription(description);
            to.setDate(date);
            return to;
        } catch (IllegalArgumentException e){
            throw new ValidateRunTimeException(ConstantsJSP.DATE_FORMAT_ERROR + strDate);
        }

    }

    public static TransferObject checkTasksId (Map<String, String[]> params){
        String[] strIdTasks = params.get(ConstantsJSP.TASKS_KEY);
        if (strIdTasks==null) throw new ValidateRunTimeException(ConstantsJSP.EMPTY_TASK_ARRAY_ERROR);
        try {
            TransferObject to = new TransferObject();
            int[] tasksIds =  Arrays.stream(strIdTasks).
                    mapToInt(Integer::parseInt).
                    toArray();
            to.setTaskIds(tasksIds);
            return to;
        } catch (NumberFormatException e) {
            String taskIds = String.join(ConstantsJSP.DELIMETER, strIdTasks);
            throw new ValidateRunTimeException(ConstantsJSP.TASK_ARRAY__NUMBER_ERROR + taskIds);
        }
    }
}
