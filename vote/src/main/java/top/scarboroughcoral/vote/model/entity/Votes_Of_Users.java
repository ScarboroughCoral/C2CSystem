package top.scarboroughcoral.vote.model.entity;

import top.scarboroughcoral.vote.model.entity.composite_id.Votes_Of_UsersId;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "votes_of_users")
@IdClass(Votes_Of_UsersId.class)
public class Votes_Of_Users {
    @Id
    private Integer user_id;
    @Id
    private Integer vote_id;

    private Integer vote_status;

    public Votes_Of_Users() {
    }

    public Votes_Of_Users(Integer voteId, Integer id) {
        this.user_id = id;
        this.vote_id = voteId;
        this.vote_status = 0;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getVote_id() {
        return vote_id;
    }

    public void setVote_id(Integer vote_id) {
        this.vote_id = vote_id;
    }

    public Integer getVote_status() {
        return vote_status;
    }

    public void setVote_status(Integer vote_status) {
        this.vote_status = vote_status;
    }
}
