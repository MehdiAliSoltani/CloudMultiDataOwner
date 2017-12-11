/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package POJO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * User of the system
 * @author maz
 */
public class Member implements Serializable {

    //binary format 0b-----dus u instead upload and d instead downlaod ans s instead of search
    protected int userId;
    protected String userName;
    protected String password;
    protected String email;
    protected String emailPassword;
    protected Groups group;
    
    
    protected Member usr;
    private List<FileKey> userFileKeys = null;


    public Member(int userId, String userName, String password, String email,
            String emailPassword) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.emailPassword = emailPassword;
       if (userFileKeys == null) {
            userFileKeys = new ArrayList<FileKey>();
        }
    }
    
  
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmailPassword() {
        return emailPassword;
    }

    public void setEmailPassword(String emailPassword) {
        this.emailPassword = emailPassword;
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

    public Groups getGroup() {
        return group;
    }

    public void setGroup(Groups group) {
        this.group = group;
    }


    public void addKey(FileKey key){
        userFileKeys.add(key);
    }
    
    public String getKey(int fileId){
        for(FileKey fk : userFileKeys){
            if(fk.getFileId() == fileId){
                return fk.getKey();
            }
        }
        return ConstAndVars.INVALID_KEY;
    }
    
    public void deleteKey(int fileId){
        for(FileKey fk : userFileKeys){
            if(fk.getFileId() == fileId){
                userFileKeys.remove(fk);
                break;
            }
        }       
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
