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
        rentalStation.setNumber(bikeNum);
        rentalStation.setLocation(location);

        System.out.println("謝謝!  BIKE：" + bikeNum + ", 地點：" + location + "\n");

        System.out.println("情境提示：將車推進停車卡槽  請以<ENTER>模擬");
        scan.nextLine();
        Sensor sensor=new Sensor();
        Command sensoroff =new SensorOffCom(sensor);
        rentalStation.operatorRent.setCommand(sensoroff);
        rentalStation.operatorRent.usebike();

        rentalStation.operatorRent.showTapCard(1);
        System.out.println("情境提示：將卡片放置於感應區感應  <手動輸入卡號>");
        //String cardNumber = sc.nextLine();
        String cardNumber = rentalStation.operatorRent.readCardNumber();

        //
        if (rentalStation.accessCenter.isAlreadyBind(cardNumber)) {
            if (rentalStation.accessCenter.isAlreadyRental(cardNumber)) {
                rentalStation.returnBike(cardNumber);
                rentalStation.showRentResult(rentalStation.RC_RETURN_SUCCESS);
            }
            else {
                rentalStation.operatorRent.standUnlock();
                rentalStation.showRentResult(rentalStation.RC_RENT_NOT_YET);
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
