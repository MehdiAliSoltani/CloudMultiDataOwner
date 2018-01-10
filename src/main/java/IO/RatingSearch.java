/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IO;

import POJO.ConstAndVars;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author mehdi
 */
public class RatingSearch {

    private String getFileName(String fileName) {
        return ConstAndVars.HOME_DIR + fileName + ".txt";
    }

    public int findPatternInFile(String pattern, String fileName) {
        String fn = getFileName(fileName);
        String fileContents = convertAFileToString(fn);
        if (fileContents != null) {
            int c = fileContents.toUpperCase().split(pattern.toUpperCase()).length - 1;
            return c;
        } else {
            return 0;
        }
    }

    private String convertAFileToString(String fileName) {
        File file = new File(fileName);
        StringBuffer sb = null;
        try {
            Scanner scanner = new Scanner(file);
            sb = new StringBuffer();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                sb.append(line);
            }
        } catch (FileNotFoundException e) {
            //handle this
        }
        if (sb == null) {
            return null;
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        RatingSearch rs = new RatingSearch();
        int r = rs.findPatternInFile("MIICvD", "/home/mehdi/1.txt");
        System.out.println(r);
    }
}
