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

            //紀錄 姓名電話
            MemberRecord memberRecord=new MemberRecord();
            memberRecord.setName(sc.next());
            memberRecord.setPhone(sc.next());

            //確認資料正確性
            AccessCenter accessCenter=new AccessCenter();
            if(accessCenter.isMemberInfoCorrect(memberRecord.getName(),memberRecord.getPhone())==true)
                operatorReg.showCardTap();

            //感應悠遊卡
            String cr=sc.next();
            if(cr.equals("1")){
                operatorReg.manualPin(sc.next());
                System.out.println("1");
            }
            else{
                CardReader cardReader=new CardReader();
                cardReader.readCardNumber(cr);
                System.out.println(cr);
            }
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