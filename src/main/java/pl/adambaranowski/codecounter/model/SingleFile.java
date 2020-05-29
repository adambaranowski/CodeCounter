package pl.adambaranowski.codecounter.model;

import java.io.Serializable;

public class SingleFile implements Serializable {

    private int linesOfCode;
    private String title;
    private Extension extension;

    public SingleFile( String title, Extension extension,int linesOfCode) {
        this.linesOfCode = linesOfCode;
        this.title = title;
        this.extension = extension;
    }

    public SingleFile() {
    }

    public int getLinesOfCode() {
        return linesOfCode;
    }

    public void setLinesOfCode(int linesOfCode) {
        this.linesOfCode = linesOfCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Extension getExtension() {
        return extension;
    }

    public void setExtension(Extension extension) {
        this.extension = extension;
    }
}
