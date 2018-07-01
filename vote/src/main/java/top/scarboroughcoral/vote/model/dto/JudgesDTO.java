package top.scarboroughcoral.vote.model.dto;

import java.util.List;

public class JudgesDTO {

    private Integer user_id;

    private boolean meetingable;

    private boolean votable;

    private String login_status;

    private String username;

    private Integer user_priority;

    private List<Integer> group_ids;

    public JudgesDTO() {
    }

    public JudgesDTO(Integer user_id, boolean meetingable, boolean votable, String login_status, String username, Integer user_priority, List<Integer> group_ids) {
        this.user_id = user_id;
        this.meetingable = meetingable;
        this.votable = votable;
        this.login_status = login_status;
        this.username = username;
        this.user_priority = user_priority;
        this.group_ids = group_ids;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public boolean isMeetingable() {
        return meetingable;
    }

    public void setMeetingable(boolean meetingable) {
        this.meetingable = meetingable;
    }

    public boolean isVotable() {
        return votable;
    }

    public void setVotable(boolean votable) {
        this.votable = votable;
    }

    public String getLogin_status() {
        return login_status;
    }

    public void setLogin_status(String login_status) {
        this.login_status = login_status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getUser_priority() {
        return user_priority;
    }

    public void setUser_priority(Integer user_priority) {
        this.user_priority = user_priority;
    }

    public List<Integer> getGroup_ids() {
        return group_ids;
    }

    public void setGroup_ids(List<Integer> group_ids) {
        this.group_ids = group_ids;
    }
}
