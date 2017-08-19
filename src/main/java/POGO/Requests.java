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
public class Requests  implements Serializable{
    private GroupMember groupMem;
    private Group group;

    public Requests(GroupMember groupMem, Group group) {
        this.groupMem = groupMem;
        this.group = group;
    }

    public GroupMember getGroupMem() {
        return groupMem;
    }

    public Group getGroup() {
        return group;
    }
    
}
