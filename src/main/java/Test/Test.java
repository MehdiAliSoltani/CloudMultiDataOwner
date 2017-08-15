/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import POGO.User;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author mehdi
 */
public class Test {
    public static void main(String[] args) {
      List<User> u = new ArrayList<User>();
      User u1 = new User(1,"ali","123");
      User u2 = new User(2,"mehdi","1663");
      User u3 = new User(3,"zahra","6663");
      User u4 = new User(4,"hamid","3555");
      User u5 = new User(5,"hani","12355");
      u.add(u5);
      u.add(u4);
      u.add(u3);
      u.add(u2);
      u.add(u1);
      u.stream().map(x -> x.getUserName()).forEach(System.out::println);
        removeList(u, u5);
        
        u.stream().map(x -> x.getUserName()).forEach(System.out::println);
    }
    private static  void removeList(List<User> users ,User usr){
        for(User user : users){
            if(user.equals(usr)){
                users.remove(usr);
//                System.out.println(usr.getUserName());
                break;
            }
        }
    }
}
