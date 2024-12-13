/* 413226087 李柏翰 */
/* 413085043 謝慶章 */

import java.util.*;

/**
 * 
 */
public class AccountStation extends Station {

    public OperatorReg operatorReg = new OperatorReg();
    public AccessCenter accessCenter = new AccessCenter();
    public AccessCenterProxy accessCenterproxy = new AccessCenterProxy();

    /**
     * Default constructor
     */
    public AccountStation(String location) {
        // 實驗性質, number 使用隨機方式產生.
        Random rand = new Random();
        setNumber(rand.nextInt(1000) + 1);
        setLocation(location);
    }

    /**
     * @param account
     */
    public void registerAccount(String name,String phone,String pwd) {
        // TODO implement here
        Scanner scan = new Scanner(System.in);

        if(accessCenterproxy .isMemberInfoCorrect(phone) == true){
            accessCenter.createMember(name, phone, pwd);
        }
        else {
            operatorReg.showError();
            operatorReg.showAddInfo(0); //顯示 輸入會員資料

            //輸入 姓名 電話 密碼
            registerAccount(scan.next(), scan.next(), scan.next()); //這裡也會判斷資料正確性
        }
    }

    public boolean checkValidityMember(String cardNumber){
         return accessCenterproxy .isValidityMember(cardNumber); //判斷會員資格是否存在
    }

    /**
     * @param account
     */
    public void unregisterAccount(String account, String password) {
        // TODO implement here
        if (!accessCenterproxy .removeMember(account, password))
            System.out.println("Account unregistered failed.");
    }

    /**
     * @param cardnumber
     */
    public boolean checkCard(String cardNumber) {
        // TODO implement here
        if(accessCenterproxy .isAlreadyBind(cardNumber)==true){     //判斷卡片是否綁定過
            System.out.println("此卡片已綁定過");
            return true;
        }
        else{
            operatorReg.showBindOrNot();
            return false;
        }
    }

    public void cardBinding(String name,String cardNumber) {
        // TODO implement here
        accessCenter.bindingCard(name, cardNumber);     //綁定卡片
    }
    /**
     * @param card
     */
    public void cardUnbind(String name) {
        // TODO implement here
        accessCenter.unbindCard(name);
    }

    /**
     * @param cardnumber
     */
    public void queryRentalRecords(String cardnumber,int qint) {
        // TODO implement here
        accessCenter.db.showRentHistory(cardnumber,qint);   //顯示租借紀錄
    }

}