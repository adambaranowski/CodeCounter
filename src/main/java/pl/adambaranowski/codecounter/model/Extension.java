package pl.adambaranowski.codecounter.model;

public enum Extension {
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
    TXT("SimpleText");




    private final String fileType;

    private Extension(String fileType){
        this.fileType=fileType;
    }
}
