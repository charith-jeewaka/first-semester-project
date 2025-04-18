package lk.ijse.florist_pos.final_project.dto;

public class SystemUserDto {
    private int userId;
    private String userName;
    private String password;
    private String userRole;
    private String userMobile;
    private String userEmail;

    public SystemUserDto() {
    }

    public SystemUserDto(int userId,String userName, String password, String userRole,
                         String userMobile, String userEmail) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.userRole = userRole;
        this.userMobile = userMobile;
        this.userEmail = userEmail;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserRole() {
        return userRole;
    }
    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getUserMobile() {
        return userMobile;
    }
    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public String getUserEmail() {
        return userEmail;
    }
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    @Override
    public String toString() {
        return SystemUserDto.class.getSimpleName() + " [userId=" + userId + ", userName=" + userName + ", password=" + password
                + ", role=" + userRole + ", userMobile=" + userMobile + ", userEmail=" + userEmail + "]";
    }
}
