package top.scarboroughcoral.vote.model.entity;

import top.scarboroughcoral.vote.model.entity.composite_id.Terminals_Of_UsersId;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.util.Date;

@Entity
@IdClass(Terminals_Of_UsersId.class)
public class Terminals_Of_Users {
    @Id
    private Integer user_id;
    @Id
    private Integer terminal_id;
    private Date login_time;
    private String login_ip;

    public Terminals_Of_Users() {

    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getTerminal_id() {
        return terminal_id;
    }

    public void setTerminal_id(Integer terminal_id) {
        this.terminal_id = terminal_id;
    }

    public Date getLogin_time() {
        return login_time;
    }

    public void setLogin_time(Date login_time) {
        this.login_time = login_time;
    }

    public String getLogin_ip() {
        return login_ip;
    }

    public void setLogin_ip(String login_ip) {
        this.login_ip = login_ip;
    }
}
