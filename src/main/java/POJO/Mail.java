/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package POJO;

import java.io.Serializable;

/**
 *
 * @author max
 * This class is a structure of a Mail
 */
public class Mail  implements Serializable{
    private int id;
    private int from;
    private int to;
    private int action;
    private int fileId;
    private boolean read;
    private boolean verify;
    private String secretKey;

    public Mail(int id,int from, int to, int mail, int fileId) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.action = mail;
        this.fileId = fileId;
        this.read = false;
        this.verify= false;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    
    
    public int getId() {
        return id;
    }

    public int getFileId() {
        return fileId;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
    }

    
    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public boolean isVerify() {
        return verify;
    }

    public void setVerify(boolean verify) {
        this.verify = verify;
    }

    
    
}
