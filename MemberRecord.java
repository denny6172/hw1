/* 413226087 李柏翰 */
/* 413085043 謝慶章 */

import java.io.*;
import java.util.*;

/**
 * 
 */
public class MemberRecord {

    /**
     * Default constructor
     */
    public MemberRecord() {
    }

    /**
     * 
     */
    private String menberID;

    /**
     * 
     */
    private String password;

    /**
     * 
     */
    private Boolean validityStatus;

    /**
     * 
     */
    private String name;

    /**
     * 
     */
    private String phone;

    /**
     * 
     */
    public IDInfo idInfo;

    /**
     * 
     */
    public CardInfo cardInfo;

    /**
     * @return
     */
    public String getMemberID() {
        // TODO implement here
        return "";
    }

    /**
     * @param memberID
     */
    public void setMemberID(String memberID) {
        // TODO implement here
    }

    /**
     * @return
     */
    public String getPassword() {
        // TODO implement here
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        // TODO implement here
        this.password=password;
    }

    /**
     * @return
     */
    public Boolean isValidity() {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public String getName() {
        // TODO implement here
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        // TODO implement here
        this.name=name;
    }

    /**
     * @return
     */
    public String getPhone() {
        // TODO implement here
        return phone;
    }

    /**
     * @param phone
     */
    public void setPhone(String phone) {
        // TODO implement here
        this.phone=phone;
    }

}