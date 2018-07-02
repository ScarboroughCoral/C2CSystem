package top.scarboroughcoral.c2c.model.entity;

import top.scarboroughcoral.c2c.model.entity.composite_id.Roles_Of_UsersId;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@IdClass(Roles_Of_UsersId.class)
public class Roles_Of_Users {
    @Id
    private Integer role_id;
    @Id
    private Integer user_id;

    public Roles_Of_Users() {
    }

    public Roles_Of_Users(Integer user_id, Integer role) {
        this.user_id = user_id;
        this.role_id = role;
    }

    public Integer getRole_id() {
        return role_id;
    }

    public void setRole_id(Integer role_id) {
        this.role_id = role_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }
}
