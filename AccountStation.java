
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
     * @param card
     */
    public void cardBinding(AccessCenter card) {
        // TODO implement here
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