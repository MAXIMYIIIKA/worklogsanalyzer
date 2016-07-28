package classes.instances;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.*;

/**
 * Created by Max Nichipor on 26.07.2016.
 */
public class Analyzer {
    private WorkLogWorkbook workbook;
    private List<User> users;
    private List<Project> projects;


    /**
     * @param workbook
     */
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

    /**
     * @param username
     * @return user
     */
    public User getUser(String username){
        for(User user: this.users){
            if(user.getUsername().equals(username)){
                return user;
            }
        }
        return null;
    }

    /**
     * @param projectName
     * @return project
     */
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

    private void sortAll(){
        Collections.sort(users, (o1, o2) -> {
            if (o1.getUsername().length() != o2.getUsername().length()){
                return String.valueOf(o1.getUsername().length()).compareTo(
                        String.valueOf(o2.getUsername().length()));
            }
            return o1.getUsername().compareTo(o2.getUsername());
        });
        Collections.sort(projects, (o1, o2) -> {
            if (o1.getProjectName().length() != o2.getProjectName().length()){
                return String.valueOf(o1.getProjectName().length()).compareTo(
                        String.valueOf(o2.getProjectName().length()));
            }
            return o1.getProjectName().compareTo(o2.getProjectName());
        });
    }

    /**
     *  This method analyze the workbook
     */
    public void analizeWorklogs(){
        Sheet sheet = workbook.getWorklogsSheet();
        Iterator<Row> rowIterator = sheet.rowIterator();
        rowIterator.next();
        while (rowIterator.hasNext()){
            Row row = rowIterator.next();
            if(getProject(getProjectKeyCellValue(row)) == null) {
                projects.add(new Project(getProjectKeyCellValue(row)));
            }
            if (getUser(getUsernameCellValue(row)) == null) {
                users.add(new User(getUsernameCellValue(row)));
            }
            getUser(getUsernameCellValue(row)).addWork(getProject(getProjectKeyCellValue(row)), getBilledHoursCellValue(row));
        }
        sortAll();
    }
}
