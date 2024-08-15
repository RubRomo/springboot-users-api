package com.ruben.crud2.dto;

public class UserDTO {

    private int id;
    private String nameSafe;
    private String lastnameSafe;
    private String usernameSafe;
    private String emailSafe;
    private String roleSafe;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameSafe() {
        return nameSafe;
    }

    public void setNameSafe(String nameSafe) {
        this.nameSafe = nameSafe;
    }

    public String getLastnameSafe() {
        return lastnameSafe;
    }

    public void setLastnameSafe(String lastnameSafe) {
        this.lastnameSafe = lastnameSafe;
    }

    public String getEmailSafe() {
        return emailSafe;
    }

    public void setEmailSafe(String emailSafe) {
        this.emailSafe = emailSafe;
    }

    public String getUsernameSafe() {
        return usernameSafe;
    }

    public void setUsernameSafe(String usernameSafe) {
        this.usernameSafe = usernameSafe;
    }

    public String getRoleSafe() {
        return roleSafe;
    }

    public void setRoleSafe(String roleSafe) {
        this.roleSafe = roleSafe;
    }
}
