/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package POGO;

import java.io.Serializable;

/**
 *
 * @author max
 * this class store information of encryption key for each file
 */

public class FileKey implements Serializable{
    private int fileId;
    private String key;

    public FileKey(int fileId, String key) {
        this.fileId = fileId;
        this.key = key;
    }

    public int getFileId() {
        return fileId;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
    
}
