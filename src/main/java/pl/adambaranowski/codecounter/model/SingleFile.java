package pl.adambaranowski.codecounter.model;

import java.io.Serializable;

public class SingleFile implements Serializable {

    private int linesOfCode;
    private String title;
    private String code;
    private Extension extension;



    /**
     * Builder of SingleFile class
     */
    public static class SingleFileBuilder{

        //Default values
        private int linesOfCode = 0;
        private String title = "Err: Not Defined";
        private String code = "";
        private Extension extension = Extension.UNDEFINED;

        private SingleFileBuilder(){}

        public SingleFileBuilder title(String title){
            this.title = title;
            return this;
        }
        public SingleFileBuilder code(String code){
            this.code = code;
            return this;
        }
        public SingleFileBuilder extension(Extension extension){
            this.extension = extension;
            return this;
        }
        public SingleFileBuilder linesOfCode(int linesOfCode){
            this.linesOfCode = linesOfCode;
            return this;
        }

        public SingleFile build(){

            //Here could be some checking if all arguments are correct

            return new SingleFile(title,code,extension,linesOfCode);
        }


    }

    public static SingleFileBuilder builder(){
        return new SingleFileBuilder();
    }

    public SingleFile() {
    }

    public SingleFile(String title, String code, Extension extension,int linesOfCode) {
        this.linesOfCode = linesOfCode;
        this.title = title;
        this.code = code;
        this.extension = extension;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
