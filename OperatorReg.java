/* 413226087 李柏翰 */
/* 413085043 謝慶章 */

import java.io.*;
import java.util.*;

/**
 * 
 */
public class OperatorReg extends CardReader {

    /**
     * Default constructor
     */
    public OperatorReg() {

    }

    /**
     * 
     */
    public void showAddInfo(int type) {
        // TODO implement here
        if (type == 0)
            System.out.println("請依序輸入 姓名 電話(10碼) 以及 密碼(至多15碼)");
        else if (type == 1)
            System.out.println("請輸入 姓名");
        else if (type == 2)
            System.out.println("請依序輸入 姓名 以及 密碼(至多15碼)");
        else if (type == 3)
            System.out.println("請依序輸入 姓名 以及 卡號(至多15碼)");
    }

    /**
     * 
     */
    public void showError() {
        // TODO implement here
        System.out.println("資料錯誤或會員已註冊");
    }

    /**
     * 
     */
    public void showCardTap() {
        // TODO implement here
        //System.out.println("請感應悠遊卡 若無法感應,請輸入 1 改為手動輸入");
        System.out.println("情境提示：請將卡片放置於感應區感應  <手動輸入卡號>");
    }

    /**
     * 
     */
    public void showCardFinish() {
        // TODO implement here
        System.out.println("卡片綁定成功");
    }

    /**
     * 
     */
    public void showQueryChoise() {
        // TODO implement here
        System.out.println("查詢一周內紀錄請輸入 1");
        System.out.println("查詢一個月內紀錄請輸入 2");
        System.out.println("查詢半年內紀錄請輸入 3");
    }

    /**
     * 
     */
    public void showHistoryRental() {
        // TODO implement here
    }

    /**
     * 
     */
    public void showBindOrNot() {
        // TODO implement here
        System.out.println("卡片正確 , 輸入 1 確定綁定");
    }

    /**
     * 
     */
    public void callService() {
        // TODO implement here
        System.out.println("已向客服進行反應");
    }

    /**
     * @return
     */
    public String manualPin() {
        // TODO implement here
        System.out.println("請手動輸入悠遊卡號碼");
        Scanner scan = new Scanner(System.in);
        return scan.nextLine();
    }

}