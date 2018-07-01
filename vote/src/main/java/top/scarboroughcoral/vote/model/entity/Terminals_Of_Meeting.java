package top.scarboroughcoral.vote.model.entity;

import top.scarboroughcoral.vote.model.entity.composite_id.Terminals_Of_MeetingId;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@IdClass(Terminals_Of_MeetingId.class)
public class Terminals_Of_Meeting {
    @Id
    private Integer meeting_id;
    @Id
    private String terminal_id;

    public Terminals_Of_Meeting() {
    }

    public Terminals_Of_Meeting(Integer meetingId, String id) {
        this.meeting_id = meetingId;
        this.terminal_id = id;
    }

    public Integer getMeeting_id() {
        return meeting_id;
    }

    public void setMeeting_id(Integer meeting_id) {
        this.meeting_id = meeting_id;
    }

    public String getTerminal_id() {
        return terminal_id;
    }

    public void setTerminal_id(String terminal_id) {
        this.terminal_id = terminal_id;
    }
}
