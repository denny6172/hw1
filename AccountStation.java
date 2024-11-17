/* 413226087 李柏翰 */
/* 413085043 謝慶章 */

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
        else {
            OperatorReg operatorReg=new OperatorReg();
            operatorReg.showError();
            operatorReg.showAddInfo(); //顯示 輸入會員資料

            //輸入 姓名 電話 密碼
            AccountStation accountStation=new AccountStation();
            Scanner sc = new Scanner(System.in);
            accountStation.registerAccount(sc.next(), sc.next(),sc.next()); //這裡也會判斷資料正確性
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
            OperatorReg operatorReg=new OperatorReg();
            operatorReg.showBindOrNot();
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
        databaseConnect.showHistory(cardnumber,qint);
    }

}