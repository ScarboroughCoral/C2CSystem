package top.scarboroughcoral.c2c.model.entity.composite_id;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

public class Terminals_Of_MeetingId implements Serializable {
    private Integer meeting_id;
    private String terminal_id;

}
