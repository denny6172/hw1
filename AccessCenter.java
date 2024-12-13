/* 413226087 李柏翰 */
/* 413085043 謝慶章 */

import java.util.*;

/**
 * 
 */
public class AccessCenter implements Bool{

    public DatabaseConnect db = new DatabaseConnect();
    /**
     * Default constructor
     */
    public AccessCenter() {
    }

    /**
     * 
     */
    //public Set<MemberRecord> member;

    /**
     * 
     */
    //public Set<RentalRecord> records;

    /**
     * @param name
     * @param pwd 
     * @return
     */
    public void createMember(String name, String phone, String pwd) {
        // TODO implement here
        //MemberRecord memberRecord = new MemberRecord();
        //memberRecord.setName(name);
        //memberRecord.setPhone(phone);
        //memberRecord.setPassword(pwd);
        db.insertMember(name, phone, pwd);
    }

    /**
     * @param uid 
     * @return
     */
    public boolean removeMember(String uid, String pwd) {
        // TODO implement here
        //return db.removeMember(uid, pwd);
        /**
         Body   處理非判斷的動作
         **/
        return true;
    }

    /**
     * @return
     */
    public boolean isMemberInfoCorrect(String phone) {
        // TODO implement here
        //跟sql判斷有無一樣的phone
        /**
         Body   處理非判斷的動作
         **/
        return true;
    }

    /**
     * @param cardNumber 
     * @return
     */
    public boolean isValidityMember(String cardNumber) {
        // TODO implement here
        //DatabaseConnect databaseConnect=new DatabaseConnect();
//        if(db.checkMember(cardNumber) == true)
//            return true;
//        else
//            return false;
        /**
         Body 處理非判斷的動作
         **/
        return true;
    }

    /**
     * @param cardInfo
     */
    public void bindingCard(String name, String card) {
        // TODO implement here
        db.updateCardNum(name, card);
    }

    /**
     * @param cardInfo
     */
    public void unbindCard(String name) {
        // TODO implement here
        db.updateCardNum(name, null);
    }

    /**
     * @return
     */
    public boolean isAlreadyBind(String cardNumber) {
        // TODO implement here
//        DatabaseConnect databaseConnect = new DatabaseConnect();
//        if (databaseConnect.checkInfo(cardNumber) == true)
//            return true;
//        else
//            return false;
        /**
         Body   處理非判斷的動作
         **/
        return true;
    }

    /**
     * @param cardNumber 
     * @param rentTime 
     * @param rentLocaton
     */
    public void rentalBike(String cardNumber, int bikeNum, String location) {
        // TODO implement here
        db.insertRental(cardNumber, bikeNum, location);
    }

    /**
     * @param cardNumber 
     * @param rentTime 
     * @param rentLocation 
     * @param rentRecord
     */
    public void returnBike(String cardNumber, int bikeNum, String location) {
        // TODO implement here
        db.updateReturn(cardNumber, bikeNum, location);
    }

    /**
     * @param cardNumber 
     * @return
     */
    public boolean isAlreadyRental(String cardNumber) {
        // TODO implement here
        //DatabaseConnect databaseConnect = new DatabaseConnect();
        //if (databaseConnect.checkRentState(cardNumber))
//        if (db.checkRentState(cardNumber))
//            return true;
//        else
//            return false;
        /**
         Body 處理非判斷的動作
         **/
        return true;
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