package pl.adambaranowski.codecounter.functions;

import pl.adambaranowski.codecounter.model.ProjectsDb;

public interface ProjectsDbService {

    ProjectsDb loadProjectsDb();

    void saveProjectDb(ProjectsDb projectsDb);
}
