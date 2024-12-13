/* 413226087 李柏翰 */
/* 413085043 謝慶章 */

import java.util.Scanner;

public class Main_Return {

    public static String location = "輔仁";

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        RentalStation rentalStation = new RentalStation(location);

        System.out.println("<<<  Return 歸還  >>>");

        System.out.println("預設條件：請優先預設BIKE車號及地點  <空白分隔>");
        int bikeNum = scan.nextInt();
        String location = scan.nextLine().strip();
        rentalStation.setNumber(bikeNum);       //預設車號
        rentalStation.setLocation(location);    //預設地點

        System.out.println("謝謝!  BIKE：" + bikeNum + ", 地點：" + location + "\n");

        System.out.println("情境提示：將車推進停車卡槽  請以<ENTER>模擬");
        scan.nextLine();
        Sensor sensor=new Sensor();
        Command sensoroff =new SensorOffCom(sensor);
        rentalStation.operatorRent.setCommand(sensoroff);
        rentalStation.operatorRent.usebike();   //用command來關閉租車控制

        rentalStation.operatorRent.showTapCard(1);
        System.out.println("情境提示：將卡片放置於感應區感應  <手動輸入卡號>");
        //String cardNumber = sc.nextLine();
        String cardNumber = rentalStation.operatorRent.readCardNumber();

        //
        if (rentalStation.accessCenterproxy.isAlreadyBind(cardNumber)) {    //判斷卡片是否綁定過
            if (rentalStation.accessCenterproxy.isAlreadyRental(cardNumber)) {      //判斷是否有租借資格
                rentalStation.returnBike(cardNumber);
                rentalStation.showRentResult(rentalStation.RC_RETURN_SUCCESS);  //還車成功
            }
            else {
                rentalStation.operatorRent.standUnlock();
                rentalStation.showRentResult(rentalStation.RC_RENT_NOT_YET);    //這卡片無租的紀錄
                rentalStation.showRentResult(rentalStation.RC_DISCARD_RETURN);
            }
        }
        else {
            rentalStation.operatorRent.standUnlock();
            rentalStation.showRentResult(rentalStation.RC_DISCARD_RETURN);
        }

        // for debug
        System.out.println("\n< DATABASE >");
        rentalStation.accessCenter.db.dumpDBTable();
    }
}
