package pl.adambaranowski.codecounter.functions;

import pl.adambaranowski.codecounter.model.Project;

import java.io.File;

public interface ProjectCreator {

    Project createProject(File file, String title, int version, int allVersions);

}
