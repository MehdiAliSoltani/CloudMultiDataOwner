/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Encryption;

import java.util.Random;

/**
 *
 * @author max
 */
public class RandomKeyGenerator {

    /**
     * generating random key for each file
     * @return 
     */
    public static String generateKey() {

        Random rd = new Random();
        String abc = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "abcdefghijklmnopqrstuvwxyz"
                + "1234567890!@#$%^&*()_+=-:;?/>.<,~|}]{[";
        StringBuffer sbf = new StringBuffer();
        for (int i = 0; i < 16; i++) {
            char letter = abc.charAt(rd.nextInt(abc.length()));
            sbf.append(letter);
        }
        return sbf.toString();
    }
    /**
     * generate random file name
     * @param fileNameLength
     * @return 
     */
    public static String generateFileName(int fileNameLength) {
        Random rd = new Random();
        String abc = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "abcdefghijklmnopqrstuvwxyz"
                + "1234567890";
        StringBuffer sbf = new StringBuffer();
        for (int i = 0; i < fileNameLength; i++) {
            char letter = abc.charAt(rd.nextInt(abc.length()));
            sbf.append(letter);
        }
        return sbf.toString();
    }
/**
 * generate random password for users 
 * @param passLength
 * @return 
 */
    public static String generatePassword(int passLength){
          Random rd = new Random();
        String abc = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"               
                + "1234567890";
        StringBuffer sbf = new StringBuffer();
        for (int i = 0; i < passLength; i++) {
            char letter = abc.charAt(rd.nextInt(abc.length()));
            sbf.append(letter);
        }
        return sbf.toString();
    }
    public static void main(String[] args) {
        System.out.println(generateFileName(50));
    }

}
