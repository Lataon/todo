package by.gsu.epamlab.utils;

import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.model.bean.Task;
import org.json.CDL;
import org.json.JSONArray;


import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.stream.Collectors;

public class Utilities {

    private Utilities() {

    }

    public static String getJsonTasks(List<Task> tasks) {
        final String NULL_ARRAY = "[]";
        final String TASKS_DELIMITER = " \n";

        String result = NULL_ARRAY;
        if (tasks.size() > 0) {
            JSONArray ja = new JSONArray();
            ja.put(Constants.ID_KEY);
            ja.put(Constants.DESCR_KEY);
            ja.put(Constants.DATE_KEY);
            ja.put(Constants.FILE_NAME_KEY);

            String strTasks = tasks.stream()
                    .map(Task::toString)
                    .collect(Collectors.joining(TASKS_DELIMITER));

            JSONArray jsonEvents = CDL.toJSONArray(ja, strTasks);
            result = jsonEvents.toString();
        }
        return result;
    }

    public static String getSubmittedFileName(Part part) {
        String fileName = null;
        String contentDisposition = part.getHeader(Constants.CONTENT_DISPOSITION_HEADER_NAME);
        String[] items = contentDisposition.split(Constants.DELIMITER);
        for (String item : items) {
            if (item.trim().startsWith(Constants.FILE_NAME_KEY)) {
                fileName = item.substring(item.indexOf("=") + 2, item.length() - 1);
            }
        }
        return fileName;
    }

    public static void downloadFileViaBrowser(InputStream fileInputStream, OutputStream fileOutputStream) throws IOException {
        try {
            byte[] buffer = new byte[1024];
            int numBytesRead;
            while ((numBytesRead = fileInputStream.read(buffer)) > 0) {
                fileOutputStream.write(buffer, 0, numBytesRead);
            }
        } finally {
            fileInputStream.close();
            fileOutputStream.close();
        }
    }



}
