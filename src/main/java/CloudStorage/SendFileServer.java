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
public class SendFileServer {
    public static void main(String[] args) throws IOException {
 
        FileClient fc = new FileClient();
        fc.call(0,"/home/mehdi/block.txt");
        
      
        
    }
}
