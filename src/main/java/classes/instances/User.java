package classes.instances;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Max Nichipor on 26.07.2016.
 */
public class User {

    private static Logger logger = Logger.getLogger(User.class);

    private String username;
    private Double worked;
    private Double billed;
    private List<Project> projects;
    private Map<Project, Double> worklog;

    public User(String username){
        this.username = username;
        this.worklog = new HashMap<>();
        projects = new ArrayList<>();
    }

    public User(String username, Double worked, Double billed){
        this.username = username;
        this.worked = worked;
        this.billed = billed;
        projects = new ArrayList<>();
        this.worklog = new HashMap<>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Double getWorked() {
        return worked;
    }

    public Double getBilled() {
        return billed;
    }

    public Map<Project, Double> getWorklog() {
        return worklog;
    }

    public void addWork(Project project, Double time){
        if(!this.worklog.containsKey(project)){
            projects.add(project);
            project.addUser(this);
        }
        worklog.compute(project, (k,v) -> v == null ? time: v + time);
        logger.debug("The work at the project "+ project.getProjectName() + " for " + time + " added");
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", worked=" + worked +
                ", billed=" + billed +
                ", projects=" + projects +
                ", worklog=" + worklog +
                '}';
    }
}
