package top.scarboroughcoral.c2c.model.entity;

import top.scarboroughcoral.c2c.model.entity.composite_id.Roles_Of_UsersId;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@IdClass(Roles_Of_UsersId.class)
public class Roles_Of_Users {
    @Id
    @Column(name = "role_id")
    private Integer roleId;
    @Id
    @Column(name = "user_id")
    private Integer userId;

    public Roles_Of_Users() {
    }

    public Roles_Of_Users(Integer user_id, Integer role) {
        this.userId = user_id;
        this.roleId = role;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
