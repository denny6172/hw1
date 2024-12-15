import java.util.Scanner;

public class RentalBike implements IStrategy {

    public static String location = "輔仁";

    public void execute()
    {
        Scanner scan = new Scanner(System.in);
        RentalStation rentalStation = new RentalStation(location);

        System.out.println("<<<  Rental 租借  >>>");

        System.out.println("預設條件：請優先預設BIKE車號及地點  <空白分隔>");
        int bikeNum = scan.nextInt();
        String location = scan.nextLine().strip();
        rentalStation.setNumber(bikeNum);       //預設車號
        rentalStation.setLocation(location);    //預設地點

        System.out.println("謝謝!  BIKE：" + bikeNum + ", 地點：" + location + "\n");

        System.out.println("情境提示：按下[啟動靠卡]按鈕  <ENTER>");
        scan.nextLine();
        rentalStation.operatorRent.rentalButton();

        rentalStation.operatorRent.showTapCard(0);
        System.out.println("情境提示：將卡片放置於感應區感應  <手動輸入卡號>");
        //String cardNumber = sc.nextLine();
        //rentalStation.operatorRent.readCardNumber(cardNumber);
        String cardNumber = rentalStation.operatorRent.readCardNumber();
        //
        if (rentalStation.accessCenterproxy.isAlreadyBind(cardNumber)) {    //判斷卡片是否綁定過
            if (!rentalStation.accessCenterproxy.isAlreadyRental(cardNumber)) {     //判斷是否有租借資格
                Sensor sensor=new Sensor();
                Command sensoron =new SensorOnCom(sensor);
                rentalStation.rentalBike(cardNumber);
                rentalStation.operatorRent.setCommand(sensoron);
                rentalStation.operatorRent.usebike();           //用command來開啟租車控制
                rentalStation.operatorRent.showTakeBike();
                System.out.println("情境提示：取車, 請以<ENTER>模擬");
                scan.nextLine();
                rentalStation.showRentResult(rentalStation.RC_RENTAL_SUCCESS);  //租借成功
            }
            else {
                rentalStation.showRentResult(rentalStation.RC_RENT_IN_LEASE);   //無租借資格
            }
        }
        else {
            rentalStation.showRentResult(rentalStation.RC_CARD_INVALID);    //卡片未綁定過
        }

        // for debug
        //System.out.println("\n< DATABASE >");
        //rentalStation.accessCenter.db.dumpDBTable();
    }
}
