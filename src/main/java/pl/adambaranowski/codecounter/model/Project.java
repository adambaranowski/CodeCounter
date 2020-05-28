package pl.adambaranowski.codecounter.model;

import java.io.Serializable;
import java.util.List;

public class Project implements Serializable {

    private String title;
    private List<SingleFile> files;

    private int TotalLines;

    public Project(String title, List<SingleFile> files, int totalLines) {
        this.title = title;
        this.files = files;
        TotalLines = totalLines;
    }

    public Project() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<SingleFile> getFiles() {
        return files;
    }

    public void setFiles(List<SingleFile> files) {
        this.files = files;
    }

    public int getTotalLines() {
        return TotalLines;
    }

    public void setTotalLines(int totalLines) {
        TotalLines = totalLines;
    }
}
