package top.scarboroughcoral.vote.model.entity;


import top.scarboroughcoral.vote.model.entity.composite_id.User_Vote_ResultId;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "user_vote_result")
@IdClass(User_Vote_ResultId.class)
public class User_Vote_Result {
    @Id
    private Integer user_id;
    @Id
    private Integer column_id;
    private Integer result;

    public User_Vote_Result() {
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getColumn_id() {
        return column_id;
    }

    public void setColumn_id(Integer column_id) {
        this.column_id = column_id;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }
}
