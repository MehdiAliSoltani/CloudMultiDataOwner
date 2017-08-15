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
public class GroupMember extends User {
    //binary format 0b------du u instead upload and d instead downlaod
    private byte permissions = 0b00000000;

    public GroupMember(int userId, String userName, String password) {
        super(userId, userName, password);
    }
    
    public void changePermission(byte permis){
        this.permissions = permis;
    }
    
}
