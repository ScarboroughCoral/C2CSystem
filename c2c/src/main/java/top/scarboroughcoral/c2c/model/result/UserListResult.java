package top.scarboroughcoral.c2c.model.result;

/**
 * Created by Hw Du on 2018/6/20.
 */
public class UserListResult {
    private Integer userId;
    private String username;
    private String loginStatus;
    private Integer userPriority;
    private String loginName;
    private boolean isOnMeeting;
    private boolean isOnVote;

    public UserListResult() {
    }

    public UserListResult(Object obj) {
        Object[] objArr = (Object[]) obj;
        this.userId = (Integer) objArr[0];
        this.username = (String) objArr[1];
        this.loginStatus = (String) objArr[4];
        this.userPriority = (Integer) objArr[5];
        this.loginName = (String) objArr[6];
        this.isOnMeeting = objArr[7] != null;
        this.isOnVote = objArr[8] != null;

    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(String loginStatus) {
        this.loginStatus = loginStatus;
    }

    public Integer getUserPriority() {
        return userPriority;
    }

    public void setUserPriority(Integer userPriority) {
        this.userPriority = userPriority;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public boolean isOnMeeting() {
        return isOnMeeting;
    }

    public void setOnMeeting(boolean onMeeting) {
        isOnMeeting = onMeeting;
    }

    public boolean isOnVote() {
        return isOnVote;
    }

    public void setOnVote(boolean onVote) {
        isOnVote = onVote;
    }
}
