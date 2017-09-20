/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CloudStorage;

import java.io.IOException;

/**
 *
 * @author mehdi
 */
public class RecieveFileServer {
     public static void main(String[] args) throws IOException {
 
        
       FileClient fcc = new FileClient();
        fcc.call(1 ,"block.txt");
        
    }
}
