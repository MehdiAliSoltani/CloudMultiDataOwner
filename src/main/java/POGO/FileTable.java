/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package POGO;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author max
 * This class provide a List of Files 
 */
public class FileTable implements Serializable{
    public static List<SysFile> fileList ;
    
    public void insertFile(SysFile file){
        fileList.add(file);
    }
    
    
    public SysFile retriveFile(){
        return null;
    }
    public List<SysFile> retriveFiles(){
        return null;
    }
    
    public int getLastId(){
       return fileList.size() +1;
    }
}
