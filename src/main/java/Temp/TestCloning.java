/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Temp;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mehdi
 */
public class TestCloning {
    public static void main(String[] args) {
        Student s1 = new Student(0, "ali");
        try {
            Student s2 = (Student) s1.clone();
            System.out.println(s1);
        System.out.println(s2);
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(TestCloning.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
   
}
