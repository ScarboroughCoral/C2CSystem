package top.scarboroughcoral.vote.model.dto;

import java.util.List;

public class LoginDTO {
    private String username;
    private Integer userId;
    private List<Integer> group_ids;
    private Integer meeting_id;
    private String meetingName;

    public LoginDTO() {
    }

    public LoginDTO(String username, List<Integer> group_ids) {
        this.username = username;
        this.group_ids = group_ids;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getMeeting_id() {
        return meeting_id;
    }

    public void setMeeting_id(Integer meeting_id) {
        this.meeting_id = meeting_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Integer> getGroup_ids() {
        return group_ids;
    }

    public void setGroup_ids(List<Integer> group_ids) {
        this.group_ids = group_ids;
    }

    public LoginDTO(String username, List<Integer> group_ids, Integer meeting_id) {
        this.username = username;
        this.group_ids = group_ids;
        this.meeting_id = meeting_id;
    }

    public String getMeetingName() {
        return meetingName;
    }

    public void setMeetingName(String meetingName) {
        this.meetingName = meetingName;
    }
}
