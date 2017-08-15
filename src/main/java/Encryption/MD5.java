/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Encryption;

/**
 *
 * @author mehdi
 */

import java.security.*;
import java.math.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MD5 {
    public String getMD5(String input){
            MessageDigest m = null;
        try {
            m = MessageDigest.getInstance("MD5");
            m.update(input.getBytes(),0,input.length());
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(MD5.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return new BigInteger(1,m.digest()).toString(16);
//        System.out.println("MD5: "+new BigInteger(1,m.digest()).toString(16));
    }
    
    
    
    public static void main(String args[]) throws Exception{
        String s="This is a test";
        MessageDigest m=MessageDigest.getInstance("MD5");
        m.update(s.getBytes(),0,s.length());
        System.out.println("MD5: "+new BigInteger(1,m.digest()).toString(16));
        s= "mehdi";
        m.update(s.getBytes(),0,s.length());
        System.out.println("MD5: "+new BigInteger(1,m.digest()).toString(16));
    }
}