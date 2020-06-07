package com.brs.project.auth.Entity;

public class User {
    private String userName;
    private String email;
    private String password;
    private String userGrpCd;

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserGrpCd(String userGrpCd) {
        this.userGrpCd = userGrpCd;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getUserGrpCd() {
        return userGrpCd;
    }
}
