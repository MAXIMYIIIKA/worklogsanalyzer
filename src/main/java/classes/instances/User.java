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
        worked = 0.0;
        billed = 0.0;
    }

    public String getUsername() {
        return username;
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
        worked += time;
        billed += time;
        logger.debug("The " + username + "'s work at the project "+ project.getProjectName() + " for " + time + " added");
    }

    @Override
    public String toString() {
        String allProjects = "[";
        for (Project project: projects){
            allProjects += project.getProjectName() + ", ";
        }
        allProjects += ']';
        return "User{" +
                "username='" + username + '\'' +
                ", worked=" + worked +
                ", billed=" + billed +
                ", projects=" + allProjects +
                ", worklog=" + worklog +
                '}';
    }
}
