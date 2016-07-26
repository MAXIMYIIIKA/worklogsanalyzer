package classes.instances;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Max Nichipor on 26.07.2016.
 */
public class Analyzer {
    private WorkLogWorkbook workbook;
    private List<User> users;
    private List<Project> projects;

    public Analyzer(WorkLogWorkbook workbook){
        this.workbook = workbook;
        this.users = new ArrayList<>();
        this.projects = new ArrayList<>();
    }

    public WorkLogWorkbook getWorkbook() {
        return workbook;
    }

    public List<User> getUsers() {
        return users;
    }


    public List<Project> getProjects() {
        return projects;
    }

    public User getUser(String username){
        for(User user: this.users){
            if(user.getUsername().equals(username)){
                return user;
            }
        }
        return null;
    }

    public Project getProject(String projectName){
        for(Project project: this.projects){
            if(project.getProjectName().equals(projectName)){
                return project;
            }
        }
        return null;
    }

    private String getUsernameCellValue(Row row){
        return row.getCell(this.workbook.getWorklogsUsernameColumnIndex()).getStringCellValue();
    }

    private String getProjectKeyCellValue(Row row){
        return row.getCell(this.workbook.getWorklogsProjectKeyColumnIndex()).getStringCellValue();
    }

    private Double getBilledHoursCellValue(Row row){
        return row.getCell(this.workbook.getWorklogsBilledHoursColumnIndex()).getNumericCellValue();
    }

    public void analizeWorklogs(){
        Sheet sheet = workbook.getWorklogsSheet();
        Iterator<Row> rowIterator = sheet.rowIterator();
        Row row = rowIterator.next();
        User user = null;
        Project project = null;
        while (rowIterator.hasNext()){
            row = rowIterator.next();
            if(getProject(getProjectKeyCellValue(row)) == null) {
                projects.add(new Project(getProjectKeyCellValue(row)));
            }
            project = getProject(getProjectKeyCellValue(row));
            if (getUser(getUsernameCellValue(row)) == null) {
                users.add(new User(getUsernameCellValue(row)));
            }
            user = getUser(getUsernameCellValue(row));
            user.addWork(project, getBilledHoursCellValue(row));
        }
    }
}
