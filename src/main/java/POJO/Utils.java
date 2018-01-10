/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package POJO;

import Encryption.AES;
import Encryption.RandomKeyGenerator;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author max
 */
public class Utils {

    public List<List<String>> convertUserMemListTowD(List<Member> groupMem) {
        List<List<String>> list = new ArrayList<List<String>>();
        for (Member gm : groupMem) {
            List<String> row = new ArrayList<String>();
            row.add(Integer.toString(gm.getUserId()));
            row.add(gm.getUserName());
            row.add(gm.getPassword());
            list.add(row);
        }
        return list;
    }

    public byte setPermission(boolean ch1, boolean ch2, boolean ch3) {
        byte perm = 0b00000000;
        if (ch1) {

            perm = (byte) (perm | 0b00000001);
        }
        if (ch2) {
            perm = (byte) (perm | 0b00000010);
        }
        if (ch3) {
            perm = (byte) (perm | 0b00000100);
        }
        return perm;

    }

    public void fileUpload(File file, String userKey, String groupKey, String pathToSave,
            String description, int permission, Member owner, int groupId) {

        AES aes = new AES();
        final String EoL = System.getProperty("line.separator");
        //convert file to string 
        List<String> lines = null;
        try {
            lines = Files.readAllLines(Paths.get(file.getPath()),
                    Charset.defaultCharset());
        } catch (IOException ex) {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
        }

        StringBuilder sb = new StringBuilder();
        for (String line : lines) {
            sb.append(line).append(EoL);
        }
        final String content = sb.toString();
        //two level encryption
        String stage1 = aes.encryptText(userKey, ConstAndVars.initVector, content);
        String stage2 = aes.encryptText(groupKey, ConstAndVars.initVector, stage1);

        //encrypt file name 
        //this is done by cloud server
        String fileNameDisk = RandomKeyGenerator.generateFileName(50);
        String fileNameSys
                = aes.encrypt(ConstAndVars.CLOUD_SERVER_KEY, ConstAndVars.initVector, file.getName());

        try {
            File newTextFile = new File(pathToSave + fileNameDisk + ".txt");

            FileWriter fw = new FileWriter(newTextFile);
            fw.write(stage2);
            fw.close();

        } catch (IOException iox) {
            //do stuff with exception
            iox.printStackTrace();
        }
        FileTable ft = new FileTable();
        int fileId = ft.getLastId();
        String descr
                = aes.encrypt(ConstAndVars.CLOUD_SERVER_KEY, ConstAndVars.initVector, description);
        SysFile sysFile
                = new SysFile(fileId, fileNameDisk, fileNameSys, descr, owner);
        ft.insertFile(sysFile);
    }

    // this method is used when a user want to upload new file into the cloud storage
    public void fileUploadNew(File file, String key, String pathToSave,
            String description, Member owner) {

        AES aes = new AES();
        //read content of a  file into a string
        final String EoL = System.getProperty("line.separator");
        //convert file to string 
        List<String> lines = null;
        byte[] encoded = null;
        try {
            
//            lines = Files.readAllLines(Paths.get(file.getPath()),
//                    Charset.defaultCharset());
            
            encoded = Files.readAllBytes(Paths.get(file.getPath()));
            
        } catch (IOException ex) {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
        }

        StringBuilder sb = new StringBuilder();
/*        for (String line : lines) {
//            sb.append(line).append(EoL);
            sb.append(line);//.append(EoL);
        }*/
        
//        final String content = sb.toString();
        final String content = new String(encoded);
//        System.out.println("contents:"+content);
        //Encrypt file with a user's key
//        String stage1 = aes.encryptText(key, ConstAndVars.initVector, content);
        String stage1 = aes.encryptText(ConstAndVars.CURRENT_USER.getKey(), ConstAndVars.initVector, content);
        System.out.println("Key:"+ConstAndVars.CURRENT_USER.getKey());
        System.out.println(Utils.class.getName());
//        System.out.println(stage1);
        //encrypt file name 
        //this is done by cloud server
        String fileNameDisk = RandomKeyGenerator.generateFileName(50);
        String fileNameSys
                = aes.encrypt(ConstAndVars.CLOUD_SERVER_KEY, ConstAndVars.initVector, file.getName());

        try {
            File newTextFile = new File(pathToSave + fileNameDisk + ".txt");

            FileWriter fw = new FileWriter(newTextFile);
            fw.write(stage1);
            fw.close();

        } catch (IOException iox) {
            //do stuff with exception
            iox.printStackTrace();
        }
        FileTable ft = new FileTable();
        int fileId = ft.getLastId();
        String descr
                = aes.encrypt(ConstAndVars.CLOUD_SERVER_KEY, ConstAndVars.initVector, description);
        SysFile sysFile
                = new SysFile(fileId, fileNameDisk, fileNameSys, descr, owner);
        ft.insertFile(sysFile);
        ConstAndVars.CURRENT_USER.addKey(new FileKey(fileId, key));
    }

    public void downloadFile(int fileId, Member member) {
        String path = ConstAndVars.HOME_DIR;
        AES aes = new AES();
//        String key = member.getKey(fileId);
        String key = member.getKey();
        SysFile sysFile = null;
        for (SysFile sFile : FileTable.fileList) {
            if (sFile.getFileId() == fileId) {
                sysFile = sFile;
                break;
            }
        }
        String content = null;
        File file = new File(path + sysFile.getFileNameDisk() + ".txt");
        try {
            byte[] bytes = Files.readAllBytes(file.toPath());

            content = new String(bytes, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }

        content = aes.decryptText(key, ConstAndVars.initVector, content);
        String fileName = aes.decryptText(ConstAndVars.CLOUD_SERVER_KEY, ConstAndVars.initVector, sysFile.getFileNameSys());
        try {
            File newTextFile = new File(path + "/10" + fileName);

            FileWriter fw = new FileWriter(newTextFile);
            fw.write(content);
            fw.close();

        } catch (IOException iox) {
            //do stuff with exception
            iox.printStackTrace();
        }
    }

    public void downloadFileOthers(SysFile sfile, Member member, String key) {
        String path = ConstAndVars.HOME_DIR;
        AES aes = new AES();

        SysFile sysFile = sfile;
        String content = null;
        File file = new File(path + sysFile.getFileNameDisk() + ".txt");
        try {
            byte[] bytes = Files.readAllBytes(file.toPath());

            content = new String(bytes, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }

        content = aes.decryptText(key, ConstAndVars.initVector, content);
        String fileName = aes.decryptText(ConstAndVars.CLOUD_SERVER_KEY, ConstAndVars.initVector, sysFile.getFileNameSys());
        try {
            File newTextFile = new File(path + sfile.getCreator().getUserName() + fileName);

            FileWriter fw = new FileWriter(newTextFile);
            fw.write(content);
            fw.close();

        } catch (IOException iox) {
            //do stuff with exception
            iox.printStackTrace();
        }
    }

    public String showContent(int fileId, Member member) {
        String path = ConstAndVars.HOME_DIR;
        AES aes = new AES();
//        String key = member.getKey(fileId);
        String key = member.getKey();
        SysFile sysFile = null;
        for (SysFile sFile : FileTable.fileList) {
            if (sFile.getFileId() == fileId) {
                sysFile = sFile;
                break;
            }
        }
        String content = null;
        File file = new File(path + sysFile.getFileNameDisk() + ".txt");
        try {
            byte[] bytes = Files.readAllBytes(file.toPath());

            content = new String(bytes, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }

        content = aes.decryptText(key, ConstAndVars.initVector, content);
        return content;
    }

    public void updateFile(SysFile sysFile, Member owner, String fileContent, String path) {

        AES aes = new AES();
        int fileId = sysFile.getFileId();
//        String key = owner.getKey(fileId);
        String key = owner.getKey();
        //two level encryption
        String stage1 = aes.encryptText(key, ConstAndVars.initVector, fileContent);

        //encrypt file name 
        //this is done by cloud server
        String fileNameDisk = sysFile.getFileNameDisk();
//        String fileNameSys = sysFile.getFileNameSys();
//                = aes.encryptText(ConstAndVars.CLOUD_SERVER_KEY, ConstAndVars.initVector, file.getName());
//        String dec = aes.decryptText(key, ConstAndVars.initVector, stage1);
        try {
            File newTextFile = new File(path + fileNameDisk + ".txt");

            FileWriter fw = new FileWriter(newTextFile);
            fw.write(stage1);
            fw.close();

        } catch (IOException iox) {
            //do stuff with exception
            iox.printStackTrace();
        }

    }

    public boolean deleteFile(int fileId, Member member) {
        String path = ConstAndVars.HOME_DIR;
        SysFile sysFile = null;
        String fileNameOnDisk = null;
        for (SysFile sFile : FileTable.fileList) {
            if (sFile.getFileId() == fileId) {
                fileNameOnDisk = sFile.getFileNameDisk() + ".txt";
                //remove from member key list
                member.deleteKey(fileId);
                //remove from file list(File Table)
                FileTable.fileList.remove(sFile);

                break;
            }
        }
        File file = new File(ConstAndVars.HOME_DIR + fileNameOnDisk);
        file.delete();
        return true;
    }

    public String getFileOwner(int fileId) {
        for (SysFile sysFile : FileTable.fileList) {
            if (sysFile.getFileId() == fileId) {
                return sysFile.getCreator().getUserName();
            }
        }
        return "NO";
    }

    public String getMember(int userId) {
        for (Member mem : ConstAndVars.USERS) {
            if (mem.getUserId() == userId) {
                return mem.getUserName();
            }
        }
        return "NO";
    }

    public String getSecretKey(int fileId) {
        for (SysFile sysFile : FileTable.fileList) {
            if (sysFile.getFileId() == fileId) {
//                return sysFile.getCreator().getKey(fileId);
                return sysFile.getCreator().getKey();
            }
        }
        return null;
    }

    public void broadcastSearch(int applicantId, String keyWord) {
        AES aes = new AES();
        for (Member mem : ConstAndVars.USERS) {
            if (!mem.getUserName().equals("admin")) {
//                System.out.println("mem.getKey():"+mem.getKey());
                String encKeyWord = aes.encrypt(mem.getKey(), ConstAndVars.initVector, keyWord);
//                System.out.println(encKeyWord);
                String decKeyWord = aes.decrypt(mem.getKey(), ConstAndVars.initVector, encKeyWord);
//                System.out.println(decKeyWord);
                ConstAndVars.queryList.add(new Query(mem.getUserId(), applicantId, encKeyWord));
            }
        }
    }
}
