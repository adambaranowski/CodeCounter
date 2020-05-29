package pl.adambaranowski.codecounter.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ProjectsDb implements Serializable {
    private List<Project> allProjects;

    public ProjectsDb() {
        this.allProjects = new ArrayList<>();
    }

    public void saveProject(Project project){
        allProjects.add(project);
    }

    public List<Project> getAllProjects() {
        return allProjects;
    }

    public void clearAll(){
        allProjects.clear();
    }

    public void removeProject(Project project){
        allProjects.remove(project);
    }

    public void setAllProjects(List<Project> allProjects) {
        this.allProjects = allProjects;
    }
}
