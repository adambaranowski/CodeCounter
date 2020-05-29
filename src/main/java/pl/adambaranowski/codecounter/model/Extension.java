package pl.adambaranowski.codecounter.model;

import java.io.Serializable;

public enum Extension implements Serializable {
    JAVA("Java"),
    C("C"),
    CPP("C++"),
    PY("Python"),
    CS("C#"),
    HTML("HTML"),
    PHP("PHP"),
    CSS("CSS"),
    FXML("FXML"),
    XML("XML"),
    JS("JavaScript"),
    JSP("JavaServerPages"),
    TXT("SimpleText"),
    UNDEFINED("Undefined File");




    private final String fileType;

    private Extension(String fileType){
        this.fileType=fileType;
    }
}
