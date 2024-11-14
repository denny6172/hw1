
import java.io.*;
import java.util.*;

/**
 * 
 */
public class AccountStation extends Station {

    /**
     * Default constructor
     */
    public AccountStation() {
    }

    /**
     * @param account
     */
    public void registerAccount(String name,String phone,String pwd) {
        // TODO implement here
        AccessCenter accessCenter=new AccessCenter();
        if(accessCenter.isMemberInfoCorrect(phone)==true){
            accessCenter.createMember(name,phone,pwd);
        }
    }

    public Boolean checkValidityMember(String cardnumber){
        AccessCenter accessCenter=new AccessCenter();
        return accessCenter.isValidityMember(cardnumber);
    }

    /**
     * @param account
     */
    public void unregisterAccount(AccessCenter account) {
        // TODO implement here
    }

    /**
     * @param cardnumber
     */
    public Boolean checkcard(String cardnumber) {
        // TODO implement here
        AccessCenter accessCenter=new AccessCenter();
        if(accessCenter.isAlreadyBind(cardnumber)==true){
            System.out.println("此卡片已綁定過");
            return true;
        }
        else{
            System.out.println("卡片正確 , 輸入 1 確定綁定");
            return false;
        }
    }

//    public void cardBinding(String cardnumber) {
//        // TODO implement here
//        DatabaseConnect databaseConnect=new DatabaseConnect();
//        MemberRecord memberRecord=new MemberRecord();
//        databaseConnect.insertInfo(memberRecord.getName(),memberRecord.getPhone(),memberRecord.getPassword(),cardnumber);
//
//    }
    public void cardBinding(String name,String phone,String password ,String cardnumber) {
        // TODO implement here
        DatabaseConnect databaseConnect=new DatabaseConnect();
        MemberRecord memberRecord=new MemberRecord();
        databaseConnect.insertInfo(name,phone,password,cardnumber);
    }
    /**
     * @param card
     */
    public void cardUnbind(AccessCenter card) {
        // TODO implement here
    }

    /**
     * @param cardnumber
     */
    public void queryRentalRecords(String cardnumber,int qint) {
        // TODO implement here
        DatabaseConnect databaseConnect=new DatabaseConnect();
        databaseConnect.showhistory(cardnumber,qint);
    }

}