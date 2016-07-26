package classes.instances;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Max Nichipor on 26.07.2016.
 */
public class User {
    private String username;
    private Double worked;
    private Double billed;
    private Map<Project, Double> worklog;

    public User(){
        this.worklog = new HashMap<>();
    }

    public User(String username, Double worked, Double billed){
        this.username = username;
        this.worked = worked;
        this.billed = billed;
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

    public void setWorked(Double worked) {
        this.worked = worked;
    }

    public Double getBilled() {
        return billed;
    }

    public void setBilled(Double billed) {
        this.billed = billed;
    }

    public Map<Project, Double> getWorklog() {
        return worklog;
    }

    public void setWorklog(Map<Project, Double> worklog) {
        this.worklog = worklog;
    }

    public void addWork(Project project, Double time){
        if(!this.worklog.containsKey(project)){
            project.addUser(this);
        }
        worklog.compute(project, (k,v) -> v == null ? time: v + time);
    }
}
