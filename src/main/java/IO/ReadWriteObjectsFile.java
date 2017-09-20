/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IO;

import POGO.Member;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 *
 * @author mehdi
 */
public class ReadWriteObjectsFile {

    public void writeFile(List<? extends Object> obj, String fileName) {

        try {
            FileOutputStream f = new FileOutputStream(new File(fileName));
            ObjectOutputStream o = new ObjectOutputStream(f);

            // Write objects to file
            o.writeObject(obj);
            o.close();
            f.close();
        } catch (IOException e) {
            System.out.println("File write error");
        }
    }

    public List<? extends Object> readFile(String fileName) {
        List<? extends Object> obj = null;
        try {
            FileInputStream fi = new FileInputStream(new File(fileName));
            ObjectInputStream oi = new ObjectInputStream(fi);

            // Read objects
            obj = (List<? extends Object>) oi.readObject();

            oi.close();
            fi.close();
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found");
        }
        catch (IOException e) {
            System.out.println("File+ "+ fileName+ " read error" + e);
        }
        return obj;
    }

    public void writeFile( Object obj, String fileName) {

        try {
            FileOutputStream f = new FileOutputStream(new File(fileName));
            ObjectOutputStream o = new ObjectOutputStream(f);

            // Write objects to file
            o.writeObject(obj);
            o.close();
            f.close();
        } catch (IOException e) {
            System.out.println("File write error");
        }
    }

    public Object readFileObject(String fileName) {
         Object obj = null;
        try {
            FileInputStream fi = new FileInputStream(new File(fileName));
            ObjectInputStream oi = new ObjectInputStream(fi);

            // Read objects
            obj = (Object) oi.readObject();

            oi.close();
            fi.close();
        } catch (ClassNotFoundException e) {}
        catch (IOException e) {
            System.out.println("File read error" + e);
        }
        return obj;
    }

   
}
