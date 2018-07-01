package top.scarboroughcoral.vote.model.entity;

import top.scarboroughcoral.vote.model.entity.composite_id.Groups_Of_UsersId;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@IdClass(Groups_Of_UsersId.class)
public class Groups_Of_Users {
    @Id
    private Integer user_id;
    @Id
    private Integer user_group_id;

    public Groups_Of_Users() {
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getUser_group_id() {
        return user_group_id;
    }

    public void setUser_group_id(Integer user_group_id) {
        this.user_group_id = user_group_id;
    }
}
