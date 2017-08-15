/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package POGO;

/**
 *
 * @author mehdi
 */
public class GroupManager extends User {

    private int groupId;
    private Group group;

    public GroupManager(int userId, String userName, String password, Group group) {
        super(userId, userName, password);
        this.group = group;

    }
//    public GroupManager(User user)

    public void givePermission(GroupMember gm, byte permis) {
        for (GroupMember groupMem : this.group.getGroupMem()) {
            groupMem.changePermission(permis);
        }
    }

    public void addMem(GroupMember groupMem) {
        this.group.getGroupMem().add(groupMem);
    }

    public void removeMem(GroupMember groupMem) {
        for (GroupMember gm : group.getGroupMem()) {
            if (gm.equals(groupMem)) {
                this.group.getGroupMem().remove(groupMem);
                break;
            }
        }
    }
}
