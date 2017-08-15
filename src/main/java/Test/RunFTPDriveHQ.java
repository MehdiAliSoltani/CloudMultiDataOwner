/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

/**
 *
 * @author mehdi
 */
public class RunFTPDriveHQ  {
    public static void main(String[] args) {
        MyFTPClient myftp = new MyFTPClient();
       boolean stat = myftp.ftpConnect("www.drivehq.com", "mehdialisoltani", "mehdi76372637", 21);
        System.out.println(stat);
        
    }
}
