/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package POGO;

import java.io.Serializable;

/**
 *
 * @author mehdi
 */
//public class GroupMember extends User  implements Serializable{
public class GroupMember implements Serializable {

    //binary format 0b-----dus u instead upload and d instead downlaod ans s instead of search
    protected int userId;
    protected String userName;
    protected String password;
    protected boolean loginStatus;
    protected GroupMember usr;

    private byte permissions = 0b00000000;

    public GroupMember(int userId, String userName, String password) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.loginStatus = false;
    }

    public GroupMember(GroupMember usr) {
        this.userId = usr.getUserId();
        this.userName = usr.getUserName();
        this.password = usr.password;
        this.loginStatus = usr.loginStatus;
    }

    public byte getPermissions() {
        return permissions;
    }

    public void changePermission(byte permis) {
        this.permissions = permis;
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

    public boolean isLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(boolean loginStatus) {
        this.loginStatus = loginStatus;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else {
            return false;
        }
    }

}
