package by.gsu.epamlab.model.bean;

import java.io.InputStream;
import java.time.LocalDate;

public class Task {
    private int id;
    private String description;
    private LocalDate date;
    private String fileName;
    private InputStream fileInputStream;

    public Task(int id, String description, LocalDate date, String fileName) {
        this.id = id;
        this.description = description;
        this.date = date;
        this.fileName = fileName;
    }

    public Task (String fileName, InputStream fileInputStream) {
        this.fileName = fileName;
        this.fileInputStream = fileInputStream;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public InputStream getFileInputStream() {
        return fileInputStream;
    }

    public void setFileInputStream(InputStream fileInputStream) {
        this.fileInputStream = fileInputStream;
    }

    public String toString() {
        return String.format("%d, %s, %s, %s", id, description, date, fileName);
    }
}
