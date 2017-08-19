/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package POGO;

import IO.ReadWriteObjectsFile;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mehdi
 */
public class ConstAndVars {
    public static GroupMember CURRENT_USER; 
    public static final String USER_FILE = "/home/mehdi/user.bin";
    public static final String GROUP_FILE = "/home/mehdi/group.bin";
    public static final String REQUEST_FILE = "/home/mehdi/requests.bin";

    public static List<GroupMember> USERS;
    public static List<Group> GROUPS;
    public static List<Requests> REQUESTS;
    private ReadWriteObjectsFile rwFile;

    public ConstAndVars() {
        rwFile = new ReadWriteObjectsFile();
        Path path = Paths.get(USER_FILE);
        if (USERS == null &&  Files.exists(path)) {
            USERS = (List<GroupMember>) (List) rwFile.readFile(ConstAndVars.USER_FILE);
        }else{
            USERS = new ArrayList<GroupMember>();
        }
        path = Paths.get(GROUP_FILE);
        if (GROUPS == null && Files.exists(path)) {
            GROUPS = (List<Group>) (List) rwFile.readFile(GROUP_FILE);
        }else{
            GROUPS = new ArrayList<Group>();
        }
        path = Paths.get(REQUEST_FILE);
        if (REQUESTS == null && Files.exists(path)) {
            REQUESTS = (List<Requests>) (List) rwFile.readFile(REQUEST_FILE);
        }else{
            REQUESTS = new ArrayList<Requests>();
        }
        
    }
    public static String getCurrentUser(GroupMember g){
        String userName = null;
        for(GroupMember gm :USERS){
            if(gm.equals(g)){
                userName =gm.getUserName();
                break;
            }
        }
        return userName;
    }

}
