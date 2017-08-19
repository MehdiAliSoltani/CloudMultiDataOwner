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
public class GroupManager extends GroupMember implements Serializable {

    private int groupId;
    private GroupMember groupMem;

    public GroupManager(int userId, String userName, String password, Group group) {
        super(userId, userName, password);
        
    }

    public GroupManager( GroupMember user) {
        super(user);
    }
    
//    public GroupManager(User user)

   /* public void givePermission(GroupMember gm, byte permis) {
        for (GroupMember groupMem : this.group.getGroupMem()) {
            groupMem.changePermission(permis);
        }
    }

    public void addMem(GroupMember groupMem) {
        this.group.getGroupMem().add(groupMem);
    }
*/
    public void removeMem(GroupMember groupMem, Group group) {
        for (GroupMember gm : group.getGroupMem()) {
            if (gm.equals(groupMem)) {
                group.getGroupMem().remove(groupMem);
                break;
            }
        }
    }

}
