/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package POJO;

import IO.ReadWriteObjectsFile;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author max
 */
public class ConstAndVars {
    /**
     * CURRENT_USER is the user has been already sign in to the system
     */
    public static Member CURRENT_USER;
   /**
    * these variables keeps the path of the system information files
    */
    public static String USER_FILE;// = HOME_DIR+"user.bin";
    public static String GROUP_FILE;// = "/home/mehdi/group.bin";
    public static String REQUEST_FILE;// = "/home/mehdi/requests.bin";
    public static String FILE_TABLE;// = "/home/mehdi/fileTable.bin";
    public static String SEARCH_REQUESTS;// = "/home/mehdi/searchRequests.bin";
    public static String MAIL_FILE;// = "/home/mehdi/mails.bin";
    public static String REQUEST_ROLE_FILE;// = "/home/mehdi/role.bin";

   /**
    * initVector : initial vector for encryption
    * CLOUD_SERVER_KEY : Cloud Server Encryption Key
    */
    public static final String initVector = "RandomInitVector"; // 16 bytes IV
    public static final String CLOUD_SERVER_KEY = "RandomInitVector"; // 16 bytes IV

    public static final String INVALID_KEY = "INVALID_KEY";
    public static String HOME_DIR;
    public static final int REQUEST_TO_DOWNLOAD = 1000;
    public static final int REQUEST_TO_EDIT = 1001;
    public static final int ACCEPT_DOWNLOAD = 1002;
    public static final int ACCEPT_EDIT = 1003;

    /*
    Store information of the users 
    */
    public static List<Member> USERS; 
    public static List<RoleChange> REQUEST_ROLE;

    /*
    Stroeinformation of the groups
    */
    public static List<Groups> GROUP;
    
    public static final int OWNER = 0;
    public static final int WRITER = 1;
    public static final int MANAGERS = 2;
    public static final int OTHERS = 3;
    /*
    MAILBOX is the mail server of the system
    */
    public static List<Mail> MAILBOX;
    /**
     * RECIEVED_MAILS is the unread mails of every users
     */
    public static List<Mail> RECIEVED_MAILS;
//    public static List<> MAILBOX;
    private ReadWriteObjectsFile rwFile;
    public static List<Query> queryList = new ArrayList<Query>();

    public ConstAndVars() {
        getPath();
        initilize();
        //creating the groups 
        GROUP = new ArrayList<Groups>();
        GROUP.add(new Groups(1, ConstAndVars.OWNER));
        GROUP.add(new Groups(2, ConstAndVars.WRITER));
        GROUP.add(new Groups(3, ConstAndVars.MANAGERS));
        GROUP.add(new Groups(4, ConstAndVars.OTHERS));
        // load files into the memory
        rwFile = new ReadWriteObjectsFile();
        Path path = Paths.get(USER_FILE);
        if (USERS == null && Files.exists(path)) {
            USERS = (List<Member>) (List) rwFile.readFile(ConstAndVars.USER_FILE);
        } else {
            USERS = new ArrayList<Member>();
        }
        path = Paths.get(GROUP_FILE);
        if (Files.exists(path)) {
            GROUP = (List<Groups>) (List) rwFile.readFile(GROUP_FILE);
        } else {
//            GROUP = new ArrayList<Groups>();
        }
        if (GROUP.get(MANAGERS).getGroupMember().isEmpty()) {
            Member gm = new Member(0, "admin", "admin", "admin@admin.com", "1234");
            gm.setGroup(GROUP.get(MANAGERS));
            USERS.add(gm);
            GROUP.get(MANAGERS).addMember(gm);
        }
        path = Paths.get(MAIL_FILE);
        if (MAILBOX == null && Files.exists(path)) {
            MAILBOX = (List<Mail>) (List) rwFile.readFile(MAIL_FILE);
        } else {
            MAILBOX = new ArrayList<Mail>();
        }
        path = Paths.get(REQUEST_ROLE_FILE);
        if (REQUEST_ROLE == null && Files.exists(path)) {
            REQUEST_ROLE = (List<RoleChange>) (List) rwFile.readFile(REQUEST_ROLE_FILE);
        } else {
            REQUEST_ROLE = new ArrayList<RoleChange>();
        }
        path = Paths.get(FILE_TABLE);
        if (FileTable.fileList == null && Files.exists(path)) {
            FileTable.fileList = (List<SysFile>) (List) rwFile.readFile(FILE_TABLE);
        } else {
            FileTable.fileList = new ArrayList<SysFile>();
        }

    }

    
    private void getPath() {
        HOME_DIR = System.getProperty("user.home");
        String osName = System.getProperty("os.name");
        if (osName.toUpperCase().contains("WIN")) {
            HOME_DIR = HOME_DIR + "\\";
        } else if (osName.toUpperCase().contains("LIN")) {
            HOME_DIR = HOME_DIR + "/";
        }
    }

    private void initilize() {
        USER_FILE = HOME_DIR + "user.bin";
        GROUP_FILE = HOME_DIR + "group.bin";
        REQUEST_FILE = HOME_DIR + "requests.bin";
        FILE_TABLE = HOME_DIR + "fileTable.bin";
        SEARCH_REQUESTS = HOME_DIR + "searchRequests.bin";
        MAIL_FILE = HOME_DIR + "mails.bin";
        REQUEST_ROLE_FILE = HOME_DIR + "role.bin";

    }
}
