/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package POGO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author mehdi
 */
public class Group implements Serializable {

    private int groupId;
    private String groupName;
    private GroupManager groupManager;
    private List<GroupMember> members;

    public Group(int groupId, String groupName, GroupManager gm) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.groupManager = gm;
        members = new ArrayList<GroupMember>();
    }

    public void joinGroup(GroupMember groupMem) {
        members.add(groupMem);
    }

    public void leaveGroup(GroupMember groupMem) {
        for (GroupMember gm : members) {
            if (gm.equals(groupMem)) {
                members.remove(groupMem);
                break;
            }
        }
    }

    public List<GroupMember> getGroupMem() {
        return this.members;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public GroupManager getGroupManager() {
        return groupManager;
    }

    public void setGroupManager(GroupManager groupManager) {
        this.groupManager = groupManager;
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
