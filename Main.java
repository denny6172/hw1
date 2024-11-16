/* 413226087 李柏翰 */
/* 413085043 謝慶章 */

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
            String name=sc.next();
            String phone=sc.next();
            String password= sc.next();
            accountStation.registerAccount(name, phone, password); //這裡也會判斷資料正確性

            //感應悠遊卡
            operatorReg.showCardTap();
            String cr=sc.next();
            String cardnumber;
            if(cr.equals("1")){
                System.out.println("請手動輸入悠遊卡號碼");
                cardnumber= sc.next();
                operatorReg.manualPin(cardnumber);
               }
            else{
                CardReader cardReader=new CardReader();
                cardnumber= sc.next();
                cardReader.readCardNumber(cardnumber);
            }

            //綁定悠遊卡
            if(accountStation.checkcard(cardnumber)==false){
                if(sc.nextInt()==1){
                    //accountStation.cardBinding(cardnumber);
                    accountStation.cardBinding(name,phone,password,cardnumber);
                    operatorReg.showCardFinish();
                }
            }
        }
        else if(func==2){
            OperatorReg operatorReg=new OperatorReg();
            operatorReg.showCardTap();                      //顯示感應悠遊卡
            String cr=sc.next();
            if(cr.equals("1")){
                System.out.println("請手動輸入悠遊卡號碼");
                cr=sc.next();
                operatorReg.manualPin(cr);              //手動輸入悠遊卡號碼
            }
            else{
                CardReader cardReader=new CardReader();
                cardReader.readCardNumber(cr);          //感應悠遊卡號碼
            }

            AccountStation accountStation=new AccountStation();
            if(accountStation.checkValidityMember(cr)==true){    //確認卡號是否有效

                operatorReg.showQueryChoise();              //選擇查詢範圍
                int qint= sc.nextInt();
                accountStation.queryRentalRecords(cr,qint);     //1是一周 2是一個月 3是半年

                System.out.println("請輸入 1 來進行登出");
                if(sc.nextInt()==1)
                    System.out.println("登出完成");
            }
            else{
                System.out.println("無該卡資料或紀錄");
                System.out.println("輸入 1 進行重新操作 , 輸入 2 會向客服進行通報");
                if(sc.nextInt()==2)
                    operatorReg.callService();
            }
        }
    }
}