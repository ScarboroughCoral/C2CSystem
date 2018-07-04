package top.scarboroughcoral.c2c.model.dto;

import java.util.List;

public class LoginDTO {

    private String username;
    private int userID;
    private String name;

    public LoginDTO() {
    }

    public LoginDTO(String username,int userID,String name){
        this.userID = userID;
        this.username = username;
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
