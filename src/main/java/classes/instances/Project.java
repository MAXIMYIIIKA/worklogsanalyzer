package classes.instances;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Max Nichipor on 26.07.2016.
 */
public class Project {
    private String projectName;
    private List<User> users;

    public Project(){
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
        if (this.getUser(user.getUsername()) != null) {
            this.users.add(user);
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
