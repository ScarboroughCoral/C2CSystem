package top.scarboroughcoral.vote.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Meeting {

    @Id
    @GeneratedValue
    private Integer meeting_id;
    private String meeting_name;
    private String meeting_addr;
    private Date meeting_time;
    private Integer meeting_duration;
    // 0 未开会 1 正在开会 2 会议结束
    private String meeting_status;

    public Meeting() {
    }

    public Meeting(String meetingName, String meetingAddr, Long meetingTime) {
        this.meeting_addr = meetingAddr;
        this.meeting_name = meetingName;
        this.meeting_time = new Date(meetingTime);
        this.meeting_duration = 1;
        this.meeting_status = "0";
    }

    public Integer getMeeting_id() {
        return meeting_id;
    }

    public void setMeeting_id(Integer meeting_id) {
        this.meeting_id = meeting_id;
    }

    public String getMeeting_name() {
        return meeting_name;
    }

    public void setMeeting_name(String meeting_name) {
        this.meeting_name = meeting_name;
    }

    public String getMeeting_addr() {
        return meeting_addr;
    }

    public void setMeeting_addr(String meeting_addr) {
        this.meeting_addr = meeting_addr;
    }

    public Date getMeeting_time() {
        return meeting_time;
    }

    public void setMeeting_time(Date meeting_time) {
        this.meeting_time = meeting_time;
    }

    public Integer getMeeting_duration() {
        return meeting_duration;
    }

    public void setMeeting_duration(Integer meeting_duration) {
        this.meeting_duration = meeting_duration;
    }

    public String getMeeting_status() {
        return meeting_status;
    }

    public void setMeeting_status(String meeting_status) {
        this.meeting_status = meeting_status;
    }
}
