package classes.instances;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Max Nichipor on 26.07.2016.
 */
public class Project {

    private static Logger logger = Logger.getLogger(Project.class);

    private String projectName;
    private List<User> users;

    public Project(String projectName){
        this.projectName = projectName;
        this.users = new ArrayList<>();
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void addUser(User user){
        if (this.getUser(user.getUsername()) == null) {
            this.users.add(user);
            logger.debug("User " + user.getUsername() + " added to project " + this.getProjectName());
        }
    }

    public User getUser(String userName){
        for (User user: this.users) {
            if (user.getUsername().equals(userName)){
                return user;
            }
        }
        return null;
    }
}
