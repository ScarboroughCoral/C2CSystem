package top.scarboroughcoral.vote.model.entity.composite_id;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

public class Votes_Of_UsersId implements Serializable {
    private Integer user_id;
    private Integer vote_id;
}
