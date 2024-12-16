/* 413226087 李柏翰 */
/* 413085043 謝慶章 */

import java.util.Scanner;

public class QueryBike implements IStrategy {

    public static String location = "輔仁";

    public void execute() {
        AccountStation accountStation = new AccountStation(location);
        Scanner scan = new Scanner(System.in);
        String cardnumber;

        System.out.println("<<<  Rental 查詢  >>>");

        while (true) {
            accountStation.operatorReg.showCardTap();                      //顯示感應悠遊卡

            //if (scan.next().equals("1")) {
            cardnumber = accountStation.operatorReg.manualPin();              //手動輸入悠遊卡號碼
            //} else {
            //    cardnumber = accountStation.operatorReg.readCardNumber();
            //}

            if (accountStation.checkValidityMember(cardnumber) == true) {    //確認卡號是否有效

                accountStation.operatorReg.showQueryChoise();              //選擇查詢範圍
                accountStation.queryRentalRecords(cardnumber, scan.nextInt());     //1是一周 2是一個月 3是半年

                System.out.println("請輸入 1 來進行登出");
                if (scan.nextInt() == 1) {
                    System.out.println("登出完成");
                    break;
                }
            } else {
                System.out.println("無該卡資料或紀錄");
                System.out.println("輸入 1 進行重新操作 , 輸入 2 會向客服進行通報");
                if (scan.nextInt() == 2) {
                    accountStation.operatorReg.callService();
                    break;
                }
            }
        }
    }
}
