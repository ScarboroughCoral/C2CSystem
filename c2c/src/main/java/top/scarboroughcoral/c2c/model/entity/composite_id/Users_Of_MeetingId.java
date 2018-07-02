package top.scarboroughcoral.c2c.model.entity.composite_id;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

public class Users_Of_MeetingId implements Serializable {
    private Integer user_id;
    private Integer meeting_id;
}
