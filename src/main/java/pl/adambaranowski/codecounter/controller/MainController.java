package pl.adambaranowski.codecounter.controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.DirectoryChooser;
import pl.adambaranowski.codecounter.functions.ProjectSearching;
import pl.adambaranowski.codecounter.functions.ProjectsDbService;
import pl.adambaranowski.codecounter.model.Project;
import pl.adambaranowski.codecounter.model.ProjectsDb;
import pl.adambaranowski.codecounter.model.SingleFile;

import java.io.File;
import java.util.List;
import java.util.Optional;

public class MainController implements ProjectsDbService, ProjectSearching {

    private final String PROJECT_TITLE_COLUMN = "Title";
    private final String PROJECT_SIZE = "Lines of Code";
    private final String PROJECT_DATE = "Added";
    private final String PROJECT_VERSIONS= "Versions";

    @FXML
    private TextField titleTextField;
    @FXML
    private TextField directoryTextField;
    @FXML
    private Button directoryBrowseButton;
    @FXML
    private Button addProjectButton;
    @FXML
    private Button clearDbButton;
    @FXML
    private TableView<Project> projectsTable;
    private File addProject;
    private ProjectsDb projectsDb;

    public void initialize() {

        configureButtons();

        //Load projects database if exist or create new

        projectsDb = loadProjectsDb();

        fillTable(projectsDb.getAllProjects());


    }



    private void configureButtons() {
        configureDirectoryBrowseButton();
        configureTableColumns();
        configureAddProjectButton();
        configureClearDbButton();

    }



    private void configureAddProjectButton(){
        addProjectButton.setOnAction(actionEvent -> {

            if(addProject!=null) {



                Project[] existingVersions = projectsDb.getAllProjects().stream()
                        .filter(project -> project.getTitle().equals(titleTextField.getText())).toArray(Project[]::new);


                //1 is always the newest version, when adding new version of project
                //older version are being pushed down by incrementing version value
                int version = 1;
                int allVersions = 1;

                for (Project p: existingVersions
                     ) {
                    allVersions++;
                    p.setVersion(p.getVersion()+1);
                }




                Project newProject = createProject(addProject,titleTextField.getText(),version, allVersions);
                projectsDb.saveProject(newProject);

//                System.out.println("Wszystkie pliki w projekcie: ");
//                for (SingleFile s : newProject.getFiles()
//                ) {
//                    System.out.println(s.getTitle());
//                }

                saveProjectDb(projectsDb);
                fillTable(projectsDb.getAllProjects());
            }

        });
    }

    private void configureClearDbButton(){
        clearDbButton.setOnAction(actionEvent -> {
            projectsDb.clearAll();
            saveProjectDb(projectsDb);

            fillTable(projectsDb.getAllProjects());
        });
    }


    private void configureDirectoryBrowseButton() {
        directoryBrowseButton.setOnAction(actionEvent -> {
            DirectoryChooser directoryChooser = new DirectoryChooser();
            File workingDirectory = new File(System.getProperty("user.dir"));

            directoryChooser.setInitialDirectory(workingDirectory);
            directoryChooser.setInitialDirectory(workingDirectory);

            addProject = directoryChooser.showDialog(directoryBrowseButton.getScene().getWindow());

            directoryTextField.setText(addProject.getAbsolutePath());
            titleTextField.setText(addProject.getName());

        });
    }


    private void configureTableColumns() {
        TableColumn<Project, String> projectTitleColumn = new TableColumn<>(PROJECT_TITLE_COLUMN);
        projectTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        projectsTable.getColumns().add(projectTitleColumn);

        TableColumn<Project, String> projectSizeColumn = new TableColumn<>(PROJECT_SIZE);
        projectSizeColumn.setCellValueFactory(new PropertyValueFactory<>("totalLines"));
        projectsTable.getColumns().add(projectSizeColumn);

        TableColumn<Project, String> projectDateColumn = new TableColumn<>(PROJECT_DATE);
        projectDateColumn.setCellValueFactory(new PropertyValueFactory<>("commitDate"));
        projectsTable.getColumns().add(projectDateColumn);

        TableColumn<Project, String> projectVersionColumn = new TableColumn<>(PROJECT_VERSIONS);
        projectVersionColumn.setCellValueFactory(new PropertyValueFactory<>("allVersions"));
        projectsTable.getColumns().add(projectVersionColumn);

    }

    private void fillTable(List<Project> projects) {
        ObservableList<Project> items = projectsTable.getItems();
        items.clear();

        for (Project project : projects
        ) {
            //add only the latest version (version=1)
            if(project.getVersion()==1)
                items.add(project);
        }
    }


}
