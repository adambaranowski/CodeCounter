package pl.adambaranowski.codecounter.functions;

import pl.adambaranowski.codecounter.model.Extension;
import pl.adambaranowski.codecounter.model.Project;
import pl.adambaranowski.codecounter.model.SingleFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;


public interface ProjectSearching {

    default Project createProject(File file, String title) {
        if (title.length() < 1) {
            title = file.getName();
        }

        List<SingleFile> singleFiles = new ArrayList<>();
        digInProject(file, singleFiles);
        return new Project(title, singleFiles, totalProjectLines(singleFiles));
    }

    private void digInProject(File file, List<SingleFile> singleFiles) {
        File[] files = file.listFiles();
        if (files != null) {

            for (File f : files
            ) {
                if (f.isDirectory()) {
                    digInProject(f, singleFiles);
                } else {
                    singleFiles.add(singleFile(f));
                }
            }

        }

    }

    private SingleFile singleFile(File file) {
        int linesOfCode = 0;

        Extension extension;
        try {
            extension = Extension.valueOf(getExtension(file));

        } catch (IllegalArgumentException e) {

            extension = Extension.valueOf("UNDEFINED");
        }

        try (
                FileReader fileReader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(fileReader)

        ) {
            String nextLine = null;
            while ((nextLine = bufferedReader.readLine()) != null) {
                linesOfCode++;
            }

        } catch (Exception e) {
        }
        return new SingleFile(file.getName(), extension, linesOfCode);

    }

    private String getExtension(File file) {

        try {
            String s = file.getName().substring(file.getName().indexOf(".")).toUpperCase();
            s = (s.substring(1));
            return s;
        } catch (StringIndexOutOfBoundsException e) {
            return "UNDEFINED";
        }

    }

    private int totalProjectLines(List<SingleFile> singleFiles) {
        int totalLines = 0;
        for (SingleFile s : singleFiles
        ) {
            totalLines += s.getLinesOfCode();
        }
        return totalLines;
    }

}
