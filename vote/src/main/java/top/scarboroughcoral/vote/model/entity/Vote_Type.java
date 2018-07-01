package top.scarboroughcoral.vote.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "vote_type")
public class Vote_Type {
    @Id
    @GeneratedValue
    private Integer vote_type_id;
    private String vote_type_name;

    public Vote_Type() {
    }

    public Integer getVote_type_id() {
        return vote_type_id;
    }

    public void setVote_type_id(Integer vote_type_id) {
        this.vote_type_id = vote_type_id;
    }

    public String getVote_type_name() {
        return vote_type_name;
    }

    public void setVote_type_name(String vote_type_name) {
        this.vote_type_name = vote_type_name;
    }
}
