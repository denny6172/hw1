/* 413226087 李柏翰 */
/* 413085043 謝慶章 */

import java.io.*;
import java.util.*;

/**
 * 
 */
public class RentalStation extends Station {

    public OperatorRent operatorRent = new OperatorRent();
    public AccessCenter accessCenter = new AccessCenter();
    public AccessCenterProxy accessCenterproxy = new AccessCenterProxy();

    public final int RC_RENTAL_SUCCESS = 0;
    public final int RC_RETURN_SUCCESS = 1;
    public final int RC_CARD_REJECT = 2;
    public final int RC_RENT_IN_LEASE = 3;
    public final int RC_RENT_NOT_YET = 4;
    public final int RC_CARD_INVALID = 5;
    public final int RC_DISCARD_RETURN = 6;

    /**
     * Default constructor
     */
    public RentalStation(String location) {
        // 實驗性質, number 使用隨機方式產生.
        Random rand = new Random();
        setNumber(rand.nextInt(1000) + 1);
        setLocation(location);
    }

    /**
     * @param bike
     */
    public void rentalBike(String cardNumber) {
        // TODO implement here
        accessCenter.rentalBike(cardNumber, getNumber(), getLocation());
    }

    /**
     * @param bike
     */
    public void returnBike(String cardNumber) {
        // TODO implement here
        accessCenter.returnBike(cardNumber, getNumber(), getLocation());
    }

    /**
     * @param rentInfo
     */
    //public void showRentInfo(String cardNumber rentInfo) {
        // TODO implement here

    //}

    public void showRentResult(int errCode) {
        System.out.print("顯示訊息：");
        switch (errCode) {
            case RC_RENTAL_SUCCESS: System.out.println("租借成功。"); break;
            case RC_RETURN_SUCCESS: System.out.println("歸還成功。"); break;
            case RC_CARD_REJECT: System.out.println("卡片不符合租借資格。"); break;
            case RC_RENT_IN_LEASE: System.out.println("租借未還，請先歸還後再租借。"); break;
            case RC_CARD_INVALID: System.out.println("卡片無效或未綁定。"); break;
            case RC_DISCARD_RETURN: System.out.println("放棄還車，車已解鎖。"); break;
            case RC_RENT_NOT_YET: System.out.println("卡片未有租借記錄，是否拿錯卡片。"); break;
        }
    }


    /**
     * @param cardInfo
     */
    public void readCardInfo(CardReader cardInfo) {
        // TODO implement here
    }

    /**
     * @param bikeInfo
     */
    public void readBikeInfo(OperatorRent bikeInfo) {
        // TODO implement here
    }

}