
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
     * @param rocords
     */
    public void queryRentalRecords(AccessCenter rocords) {
        // TODO implement here
    }

}