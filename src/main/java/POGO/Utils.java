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
public class Utils {

    public List<List<String>> convertUserMemListTowD(List<GroupMember> groupMem) {
        List<List<String>> list = new ArrayList<List<String>>();
        for (GroupMember gm : groupMem) {
            List<String> row = new ArrayList<String>();
            row.add(Integer.toString(gm.getUserId()));
            row.add(gm.getUserName());
            row.add(gm.getPassword());
            list.add(row);
        }
        return list;
    }

    public List<List<String>> convertGroupListTowD(List<Group> group) {
        List<List<String>> list = new ArrayList<List<String>>();
        for (Group g : group) {
            List<String> row = new ArrayList<String>();
            row.add(Integer.toString(g.getGroupId()));
            row.add(g.getGroupName());
            list.add(row);
        }
        return list;
    }

    public  byte setPermission(boolean ch1, boolean ch2, boolean ch3) {
        byte perm = 0b00000000;
        if (ch1) {

            perm = (byte) (perm | 0b00000001);
        }
        if (ch2) {
            perm = (byte) (perm | 0b00000010);
        }
        if (ch3) {
            perm = (byte) (perm | 0b00000100);
        }
        return perm;

    }
}
