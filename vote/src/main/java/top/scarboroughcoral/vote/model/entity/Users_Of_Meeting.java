package top.scarboroughcoral.vote.model.entity;

import top.scarboroughcoral.vote.model.entity.composite_id.Users_Of_MeetingId;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@IdClass(Users_Of_MeetingId.class)
public class Users_Of_Meeting {
    @Id
    private Integer user_id;
    @Id
    private Integer meeting_id;

    public Users_Of_Meeting() {
    }

    public Users_Of_Meeting(Integer meetingId, Integer user_id) {
        this.user_id = user_id;
        this.meeting_id = meetingId;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getMeeting_id() {
        return meeting_id;
    }

    public void setMeeting_id(Integer meeting_id) {
        this.meeting_id = meeting_id;
    }
}
