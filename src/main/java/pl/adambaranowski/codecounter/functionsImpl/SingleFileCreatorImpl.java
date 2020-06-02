package pl.adambaranowski.codecounter.functionsImpl;

import pl.adambaranowski.codecounter.functions.SingleFileCreator;
import pl.adambaranowski.codecounter.model.Extension;
import pl.adambaranowski.codecounter.model.SingleFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class SingleFileCreatorImpl implements SingleFileCreator {

    /**
     * SingleFileCreatorImpl contains methods that are necessary to creates files.
     * Every project consists of single files
     */

    /**
     * Creates model SingleFile from given file
     *
     * @param file - given file
     * @return created model SingleFile from given file
     */

    @Override
    public SingleFile createSingleFile(File file) {
        int linesOfCode = 0;


        Extension extension;
        try {
            extension = Extension.valueOf(getExtension(file));

        } catch (IllegalArgumentException e) {

            extension = Extension.valueOf("UNDEFINED");
        }


        StringBuilder codeBuilder = new StringBuilder();
        if (extension != Extension.UNDEFINED) {

            try (
                    FileReader fileReader = new FileReader(file);
                    BufferedReader bufferedReader = new BufferedReader(fileReader)

            ) {
                String nextLine = null;
                while ((nextLine = bufferedReader.readLine()) != null) {
                    codeBuilder.append(nextLine);
                    linesOfCode++;
                }


            } catch (Exception e) {
            }
        }

        //Use builder of SingleFile
        return SingleFile.builder()
                .title(file.getName())
                .code(codeBuilder.toString())
                .extension(extension)
                .linesOfCode(linesOfCode)
                .build();

}

    /**
     * @param file - given file
     * @return extension of given file in String format
     */
    private String getExtension(File file) {

        try {
            String s = file.getName().substring(file.getName().indexOf(".")).toUpperCase();
            s = (s.substring(1));
            return s;
        } catch (StringIndexOutOfBoundsException e) {
            return "UNDEFINED";
        }
    }
}
