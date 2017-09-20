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
 * Providing a data structure for files in the system
 */
public class SysFile implements Serializable{

    private int fileId;
    private String fileNameDisk;
    private String fileNameSys;
    private String description;
    private int permission; //DWS-DWS-DWS owner-group-other ; write-download-search
    private Member owner;
    private int rankSerch;

    public SysFile(int fileId, String fileNameDisk, String fileNameSys, String description, 
            int permission, Member owner) {
        this.fileId = fileId;
        this.fileNameDisk = fileNameDisk;
        this.fileNameSys = fileNameSys;
        this.description = description;
        this.permission = permission;
        this.owner = owner;
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

    public int getPermission() {
        return permission;
    }

    public void setPermission(int permission) {
        this.permission = permission;
    }

    public Member getOwner() {
        return owner;
    }

    public void setOwner(Member owner) {
        this.owner = owner;
    }

    public int getRankSerch() {
        return rankSerch;
    }

    public void setRankSerch(int rankSerch) {
        this.rankSerch = rankSerch;
    }

    
}
