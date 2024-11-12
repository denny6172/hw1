
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
        System.out.println("請依序輸入會員資料 名字 電話");
    }

    /**
     * 
     */
    public void showAddInfo() {
        // TODO implement here
    }

    /**
     * 
     */
    public void showError() {
        // TODO implement here
    }

    /**
     * 
     */
    public void showCardTap() {
        // TODO implement here
        System.out.println("請感應悠遊卡 若無法感應,請輸入 1 改為手動輸入");
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
        System.out.println("卡片正確 , 確認是否綁定");
    }

    /**
     * 
     */
    public void callService() {
        // TODO implement here
    }

    /**
     * @return
     */
    public String manualPin(String cardId) {
        // TODO implement here
        return "";
    }

}