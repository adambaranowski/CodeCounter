package pl.adambaranowski.codecounter.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class Project implements Serializable {

    private String title;
    private List<SingleFile> files;
    private LocalDateTime commitDate;

    private int totalLines;

    public Project(String title, List<SingleFile> files, int totalLines) {
        this.title = title;
        this.files = files;
        this.totalLines = totalLines;
        commitDate = LocalDateTime.now();
    }

    public Project() {
    }

    public String getTitle() {
        return title;
    }

    public String getCommitDate(){
        return commitDate.getYear() + "-"+commitDate.getMonth()+"-"+commitDate.getDayOfMonth()+" "+
                commitDate.getHour()+":"+commitDate.getMinute()+":"+commitDate.getSecond();
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
        return totalLines;
    }

    public void setTotalLines(int totalLines) {
        totalLines = totalLines;
    }
}
