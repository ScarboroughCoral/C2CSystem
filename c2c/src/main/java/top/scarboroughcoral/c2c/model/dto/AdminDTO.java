package top.scarboroughcoral.c2c.model.dto;

public class AdminDTO {
    private String loginName;
    private String password;

    public AdminDTO() {
    }

    public AdminDTO(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
