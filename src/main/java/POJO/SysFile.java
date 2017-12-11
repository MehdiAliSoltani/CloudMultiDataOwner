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
 * Providing a data structure for files in the system
 */
public class SysFile implements Serializable{

    private int fileId;
    private String fileNameDisk;
    private String fileNameSys;
    private String description;
    
    private Member creator;
    private int rankSerch;

    public SysFile(int fileId, String fileNameDisk, String fileNameSys, String description, 
             Member owner) {
        this.fileId = fileId;
        this.fileNameDisk = fileNameDisk;
        this.fileNameSys = fileNameSys;
        this.description = description;
        
        this.creator = owner;
    }

    public int getFileId() {
        return fileId;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
    }

    public String getFileNameDisk() {
        return fileNameDisk;
    }

    public void setFileNameDisk(String fileNameDisk) {
        this.fileNameDisk = fileNameDisk;
    }

    public String getFileNameSys() {
        return fileNameSys;
    }

    public void setFileNameSys(String fileNameSys) {
        this.fileNameSys = fileNameSys;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public Member getCreator() {
        return creator;
    }

    public void setCreator(Member creator) {
        this.creator = creator;
    }

    public int getRankSerch() {
        return rankSerch;
    }

    public void setRankSerch(int rankSerch) {
        this.rankSerch = rankSerch;
    }

    
}
