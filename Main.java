import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("使用註冊功能請輸入 1, 使用查詢功能請輸入2");
        Scanner sc = new Scanner(System.in);
        int func=sc.nextInt();
        if(func==1){
            OperatorReg operatorReg=new OperatorReg();
            operatorReg.showAddInfo(); //顯示 輸入會員資料

            //輸入 姓名 電話 密碼
            AccountStation accountStation=new AccountStation();
            accountStation.registerAccount(sc.next(), sc.next(), sc.next()); //這裡也會判斷資料正確性

            //感應悠遊卡
            operatorReg.showCardTap();
            String cr=sc.next();
            if(cr.equals("1")){
                System.out.println("請手動輸入悠遊卡號碼");
                operatorReg.manualPin(sc.next());
            }
            else{
                //CardReader cardReader=new CardReader();
                //cardReader.readCardNumber(cr);
            }

            //綁定悠遊卡

        }
        else if(func==2){
            OperatorReg operatorReg=new OperatorReg();
            operatorReg.showCardTap();                      //感應悠遊卡
            String cr=sc.next();
            if(cr.equals("1")){
                cr=sc.next();
                operatorReg.manualPin(cr);
            }
            else{
                CardReader cardReader=new CardReader();
                cardReader.readCardNumber(cr);
            }


            AccessCenter accessCenter=new AccessCenter();
            if(accessCenter.isValidityMember(cr)==true){    //確認卡號是否有效
                operatorReg.showQueryChoise();              //選擇查詢範圍
                AccountStation accountStation=new AccountStation();
                int qint= sc.nextInt();
                if(qint==1){                //一周
                    //accountStation.queryRentalRecords();
                }
                if(qint==2){                //一個月

                }
                if(qint==3){                //半年

                }
            }
        }
    }
}