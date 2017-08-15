/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package POGO;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mehdi
 */
public class ConstAndVars {

    public static final String USER_FILE ="/home/mehdi/user.bin";
    public static final String GROUP_FILE ="/home/mehdi/group.bin";
    
    public static List<GroupMember> USERS;
    public static List<Group> GROUPS;

    public ConstAndVars() {
        if (USERS == null) {
            USERS = new ArrayList<GroupMember>();
        }
        if (GROUPS == null) {
            GROUPS = new ArrayList<Group>();
        }
    }
    
}
