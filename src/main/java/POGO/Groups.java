/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package POGO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author max
 * There are four types of group this class providing possibilities to operate on Groups
 */
public class Groups implements Serializable{

    private int groupId;
    private int groupType; // 1 = owner 2= writer 3= manager
    private List<Member> groupMember = new ArrayList<Member>();

    public Groups(int groupId, int groupType) {
        this.groupId = groupId;
        this.groupType = groupType;

    }

    public int getGroupId() {
        return groupId;
    }

    public int getGroupType() {
        return groupType;
    }
    

    public void addMember(Member mem) {
        groupMember.add(mem);
    }

    public List<Member> getGroupMember() {
        return this.groupMember;
    }

    public void removeMember(Member mem) {
        for (Member memb : getGroupMember()) {
            if(memb.equals(mem)){
                getGroupMember().remove(mem);
                break;
            }
        }
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
            return true;
        }
    }

}
