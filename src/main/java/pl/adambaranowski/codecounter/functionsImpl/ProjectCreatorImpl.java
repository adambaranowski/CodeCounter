package pl.adambaranowski.codecounter.functionsImpl;

import pl.adambaranowski.codecounter.functions.ProjectCreator;
import pl.adambaranowski.codecounter.model.Project;
import pl.adambaranowski.codecounter.model.SingleFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class ProjectCreatorImpl extends SingleFileCreatorImpl implements ProjectCreator {

    /**
     *
     * This class extends SingleFileCreatorImpl to use methods that allows to create single files.
     * Every project consists of single files
     *
     * ProjectCreatorImpl is implemented as a Singleton to prevent
     * creating many objects that could potentially working on the same file.
     */

    private static ProjectCreatorImpl INSTANCE;

    private ProjectCreatorImpl() {
    }

    public static ProjectCreatorImpl getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ProjectCreatorImpl();
        }
        return INSTANCE;
    }


    /**
     * Creates new Project from given params:
     *
     * @param startDirection - project main direction
     * @param title          - project title
     * @param version        - current version of project
     * @param allVersions    - how many version of the project of given title are already created
     * @return new object of Project
     */
    @Override
    public Project createProject(File startDirection, String title, int version, int allVersions) {
        if (title.length() < 1) {
            title = startDirection.getName();
        }

        List<SingleFile> singleFiles = new ArrayList<>();
        digInProjectStructure(startDirection, singleFiles);
        return new Project(version, allVersions, title, singleFiles, totalProjectLines(singleFiles));
    }

    /**
     * Recursive method
     * <p>
     * Starts working in given direction:
     *
     * @param startDirection - starting direction
     *                       <p>
     *                       Gets into project structure and adds to list of files:
     * @param singleFiles    - list of all single files
     *                       every single file.
     */
    private void digInProjectStructure(File startDirection, List<SingleFile> singleFiles) {
        File[] files = startDirection.listFiles();
        if (files != null) {

            for (File f : files
            ) {
                if (f.isDirectory()) {
                    digInProjectStructure(f, singleFiles);
                } else {
                    singleFiles.add(createSingleFile(f));
                }
            }
        }
    }

    /**
     * Count every lines of code in each single file
     * <p>
     * from list:
     *
     * @param singleFiles - list of all project files
     *                    and summarise them.
     * @return sum of all lines of code in project
     */
    private int totalProjectLines(List<SingleFile> singleFiles) {
        int totalLines = 0;
        for (SingleFile s : singleFiles
        ) {
            totalLines += s.getLinesOfCode();
        }
        return totalLines;
    }
}
