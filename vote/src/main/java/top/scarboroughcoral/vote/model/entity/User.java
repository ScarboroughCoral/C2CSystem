package top.scarboroughcoral.vote.model.entity;

import top.scarboroughcoral.vote.util.MD5Util;

import javax.persistence.*;

import static top.scarboroughcoral.vote.util.Constant.USER_STATUS_OFFLINE;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue
    private Integer user_id;

    private String loginName;

    private String username;

    private String password;

    private boolean is_delete;

    private String login_status;

    private Integer user_priority;

    public User() {
    }

    public User(String loginName,String username, String password, Integer userPriority) {
        this.username = username;
        this.loginName = loginName;
        this.password = MD5Util.getMD5(password);
        this.user_priority = userPriority;
        this.is_delete = false;
        this.login_status = USER_STATUS_OFFLINE;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isIs_delete() {
        return is_delete;
    }

    public void setIs_delete(boolean is_delete) {
        this.is_delete = is_delete;
    }

    public String getLogin_status() {
        return login_status;
    }

    public void setLogin_status(String login_status) {
        this.login_status = login_status;
    }

    public Integer getUser_priority() {
        return user_priority;
    }

    public void setUser_priority(Integer user_priority) {
        this.user_priority = user_priority;
    }
}
