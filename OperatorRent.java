/* 413226087 李柏翰 */
/* 413085043 謝慶章 */

import java.io.*;
import java.util.*;

/**
 * 
 */
public class OperatorRent extends CardReader {
    private Command command;
    private int bikeNumber;
    /**
     * Default constructor
     */
    public OperatorRent() {
        Random rand = new Random();
        bikeNumber =  rand.nextInt(100) + 1;
    }

    /**
     * @return
     */
    public boolean isBikeExist() {
        // TODO implement here
        System.out.println("    偵測到車子已進槽鎖住");
        return true;
    }

    public int readBikeNumber() {
        return bikeNumber;
    }

    /**
     * 
     */
    public void standUnlock() {
        // TODO implement here
        System.out.println("    Unlock Bike");
    }

    /**
     * 
     */
    public void rentalButton() {
        // TODO implement here
        System.out.println("    觸發啟動靠卡, 開啟感應器");
    }

    /**
     * 
     */
    public void ReturnCancel() {
        // TODO implement here
    }

    /**
     * 
     */
    public void showTapCard(int mode) {
        // TODO implement here
        if (mode == 0)
            System.out.println("顯示訊息：[靠卡租借]");
        else
            System.out.println("顯示訊息：[還車成功，請靠卡]");
        //readCardNumber();
    }

    /**
     * 
     */
    public void showTakeBike() {
        // TODO implement here
        System.out.println("顯示訊息：請取車");
    }

    /**
     * 
     */
    public void showRentalResult() {
        // TODO implement here
    }

    /**
     * 
     */
    public void showReturnResult() {
        // TODO implement here
    }

    /**
     * 
     */
    public void showCardFail() {
        // TODO implement here
    }
    /**
     *
     */
    public void setCommand(Command command){
        this.command=command;
    }
    public void usebike(){
        command.execute();
    }
}