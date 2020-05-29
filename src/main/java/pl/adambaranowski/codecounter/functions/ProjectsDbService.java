package pl.adambaranowski.codecounter.functions;

import pl.adambaranowski.codecounter.model.ProjectsDb;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public interface ProjectsDbService {

    default ProjectsDb loadProjectsDb(){

        String fileName = "ProjectsDb.db";
        ProjectsDb projectsDb = null;

        try(
                FileInputStream fis = new FileInputStream(fileName);
                ObjectInputStream ois = new ObjectInputStream(fis);
                ){

            projectsDb = (ProjectsDb)ois.readObject();
            System.out.println("Wczytano baze danych projektów");

        }catch (Exception e){
            System.out.println("Nie wczytano bazy danych projektów");
            //e.printStackTrace();
        }
        if (projectsDb==null) {
           projectsDb = new ProjectsDb();
        }
        return projectsDb;
    }

    default void saveProjectDb(ProjectsDb projectsDb){
        String fileName = "ProjectsDb.db";

        try(
                FileOutputStream fs = new FileOutputStream(fileName);
                ObjectOutputStream os = new ObjectOutputStream(fs);
                ){

            os.writeObject(projectsDb);
            System.out.println("Zapisano obiekt bazy danych do pliku");

        }catch(Exception e){
            System.out.println("Błąd przy zapisie bazy danych projektów");
            e.printStackTrace();

        }

    }
}
