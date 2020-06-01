package pl.adambaranowski.codecounter.functionsImpl;

import pl.adambaranowski.codecounter.functions.ProjectsDbService;
import pl.adambaranowski.codecounter.model.ProjectsDb;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;



public class ProjectsDbServiceImpl implements ProjectsDbService {


    private static final String DB_FILE_NAME = "ProjectsDb.db";


    /**
     * ProjectsDbService is implemented as a Singleton to prevent
     * creating many objects that could potentially connecting DataBase in the same time.
     */

    private static ProjectsDbServiceImpl INSTANCE;

    private ProjectsDbServiceImpl(){}

    public static ProjectsDbServiceImpl getInstance(){
        if(INSTANCE==null){
            INSTANCE = new ProjectsDbServiceImpl();
        }
        return INSTANCE;
    }

    /**
     *
     * @return object od DataBase (containing all projects)
     * created from serialized in file, which name is stored in DB_FILE_NAME
     */
    @Override
    public ProjectsDb loadProjectsDb() {

        ProjectsDb projectsDb = null;

        try(
                FileInputStream fis = new FileInputStream(DB_FILE_NAME);
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


    /**
     * Saves given DataBase to file, which name is specified in DB_FILE_NAME
     * @param projectsDb - DataBase to save
     */
    @Override
    public void saveProjectDb(ProjectsDb projectsDb) {
        try(
                FileOutputStream fs = new FileOutputStream(DB_FILE_NAME);
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
