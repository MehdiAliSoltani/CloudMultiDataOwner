/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Encryption;

/**
 *
 * @author max
 */
import POJO.Utils;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class AES {

    public String encrypt(String key, String initVector, String value) {
        try {
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

            byte[] encrypted = cipher.doFinal(value.getBytes());
//            System.out.println("encrypted string: "
//                    + Base64.encodeBase64String(encrypted));

            return Base64.encodeBase64String(encrypted);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public String decrypt(String key, String initVector, String encrypted) {
        try {
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

            byte[] original = cipher.doFinal(Base64.decodeBase64(encrypted));

            return new String(original);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    private String getEncryptedSpaceSpliter(String key, String initVector) {
        String spaceSpliter = "-;2.,-";
        String EncSpliter = encrypt(key, initVector, spaceSpliter);
        return EncSpliter;
    }

    private String getEncryptedNewLineSpliter(String key, String initVector) {
        String spaceSpliter = "-;3.,-";
        String EncSpliter = encrypt(key, initVector, spaceSpliter);
        return EncSpliter;
    }

    public String encryptText(String key, String initVector, String plainText) {
        return splitAndEncryptText(key, initVector, plainText);

    }

    public String decryptText(String key, String initVector, String cipherText) {
        String spaceSpliter = getEncryptedSpaceSpliter(key, initVector);
        String newLineSpliter = getEncryptedNewLineSpliter(key, initVector);
        String palinText = "";
        int start = 0;
        int spaceIndex = -1;
        int newLineIndex = -1;
        String delimiter = "";
        do {
            String word = "";
            spaceIndex = cipherText.indexOf(spaceSpliter, start);
            newLineIndex = cipherText.indexOf(newLineSpliter, start);
            if ((spaceIndex != -1 && newLineIndex != -1) && (spaceIndex < newLineIndex)) {
                word = cipherText.substring(start, spaceIndex);
                start = spaceIndex + spaceSpliter.length();
                delimiter = " ";
            } else if ((spaceIndex != -1 && newLineIndex != -1) && (spaceIndex > newLineIndex)) {
                word = cipherText.substring(start, newLineIndex);
                start = newLineIndex + newLineSpliter.length();
                delimiter = "\n";
            } else {
                if (spaceIndex > 0) {
                    word = cipherText.substring(start, spaceIndex);
                    start = spaceIndex + spaceSpliter.length();
                    delimiter = " ";
                } else if (newLineIndex > 0) {
                    word = cipherText.substring(start, newLineIndex);
                    start = newLineIndex + newLineSpliter.length();
                    delimiter = "\n";
                } else {
                    break;
                }
            }
            String tmpPlain = decrypt(key, initVector, word);
            palinText = palinText + tmpPlain + delimiter;
            delimiter = "";
     
        } while (spaceIndex != -1 && newLineIndex != -1);
       
        return palinText;
       
    }

    public static void main(String[] args) {
/*        String key = "Bar12345Bar12345"; // 128 bit key
        String initVector = "RandomInitVector"; // 16 bytes IV

        AES encAl = new AES();
        String test = "mehdi" + System.getProperty("line.separator") + "ali               soltani\\nzahra";
        StringBuffer sb = new StringBuffer();
        sb.append("mehdi").append("line.separator").append("ali").append("line.separator").append("soltani");
        final String EoL = System.getProperty("line.separator");
        //convert file to string 
        List<String> lines = null;
        File file = new File("/home/mehdi/22.txt");
        try {
            lines = Files.readAllLines(Paths.get(file.getPath()),
                    Charset.defaultCharset());
        } catch (IOException ex) {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
        }

        StringBuilder sb1 = new StringBuilder();
        for (String line : lines) {
            sb1.append(line).append(EoL);
        }
        final String content = sb1.toString();

        System.out.println("");
        String en = encAl.encryptText(key, initVector, content);
        System.out.println(en);
        System.out.println(encAl.decryptText(key, initVector, en));
*/
    }

    private String splitAndEncryptText(String key, String initVector, String text) {
        String cipherText = "";
        char[] encoded = text.toCharArray();
        String word = "";
        for (int i = 0; i < encoded.length; i++) {
            if (!((char) encoded[i] == '\n' || (char) encoded[i] == '\r' || (char) encoded[i] == ' ')) {
                word = word + (char) encoded[i];

            } else {
                String tmpEnc = encrypt(key, initVector, word);

                if (encoded[i] == ' ') {
                    cipherText = cipherText + tmpEnc + getEncryptedSpaceSpliter(key, initVector);
                } else if ((char) encoded[i] == '\n' || (char) encoded[i] == '\r') {
                    cipherText = cipherText + tmpEnc + getEncryptedNewLineSpliter(key, initVector);
                }

                word = "";
            }
        }
        return cipherText;

    }
}
