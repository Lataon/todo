package by.gsu.epamlab.model.bean;

import java.sql.Date;

public class TransferObject {
    private String description;
    private Date date;
    private int[] taskIds;

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setTaskIds(int[] taskIds) {
        this.taskIds = taskIds;
    }

    public String getDescription() {
        return description;
    }

    public Date getDate() {
        return date;
    }

    public int[] getTaskIds() {
        return taskIds;
    }
}