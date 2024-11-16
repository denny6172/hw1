/* 413226087 李柏翰 */
/* 413085043 謝慶章 */

import java.io.*;
import java.util.*;

/**
 * 
 */
public class AccessCenter {

    /**
     * Default constructor
     */
    public AccessCenter() {
    }

    /**
     * 
     */
    public Set<MemberRecord> member;

    /**
     * 
     */
    public Set<RentalRecord> records;

    /**
     * @param name
     * @param pwd 
     * @return
     */
    public void createMember(String name, String phone, String pwd) {
        // TODO implement here
        MemberRecord memberRecord=new MemberRecord();
        memberRecord.setName(name);
        memberRecord.setPhone(phone);
        memberRecord.setPassword(pwd);
    }

    /**
     * @param uid 
     * @return
     */
    public int removeMember(String uid) {
        // TODO implement here
        return 0;
    }

    /**
     * @return
     */
    public Boolean isMemberInfoCorrect(String phone) {
        // TODO implement here
        //跟sql判斷有無一樣的phone
        return true;
    }

    /**
     * @param cardNumber 
     * @return
     */
    public Boolean isValidityMember(String cardNumber) {
        // TODO implement here
        DatabaseConnect databaseConnect=new DatabaseConnect();
        if(databaseConnect.checkmember(cardNumber)==true)
            return true;
        else
            return false;
    }

    /**
     * @param cardInfo
     */
    public void bindingCard(CardInfo cardInfo) {
        // TODO implement here
    }

    /**
     * @param cardInfo
     */
    public void unbindCard(CardInfo cardInfo) {
        // TODO implement here
    }

    /**
     * @return
     */
    public Boolean isAlreadyBind(String cardnumber) {
        // TODO implement here
        DatabaseConnect databaseConnect=new DatabaseConnect();
        if(databaseConnect.checkInfo(cardnumber)==true)
            return true;
        else
            return false;
    }

    /**
     * @param cardNumber 
     * @param rentTime 
     * @param rentLocaton
     */
    public void rentalBike(String cardNumber, Date rentTime, String rentLocaton) {
        // TODO implement here
    }

    /**
     * @param cardNumber 
     * @param rentTime 
     * @param rentLocation 
     * @param rentRecord
     */
    public void returnBike(String cardNumber, Date rentTime, String rentLocation, RentalRecord rentRecord) {
        // TODO implement here
    }

    /**
     * @param cardNumber 
     * @return
     */
    public Boolean isAlreadyRental(String cardNumber) {
        // TODO implement here
        return null;
    }

    /**
     * @param cardNumber 
     * @param startTime 
     * @param endTime 
     * @param Parameter1
     */
    public void queryRentalRecords(String cardNumber, Date startTime, Date endTime, Set<RentalRecord> Parameter1) {
        // TODO implement here
    }

}