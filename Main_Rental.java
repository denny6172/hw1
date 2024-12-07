import java.util.Scanner;
import java.util.Date;

public class Main_Rental {

    public static String location = "輔仁";

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        RentalStation rentalStation = new RentalStation(location);

        System.out.println("<<<  Rental 租借  >>>");

        System.out.println("預設條件：請優先預設BIKE車號及地點  <空白分隔>");
        int bikeNum = scan.nextInt();
        String location = scan.nextLine().strip();
        rentalStation.setNumber(bikeNum);
        rentalStation.setLocation(location);

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
        if (rentalStation.accessCenter.isAlreadyBind(cardNumber)) {
            if (!rentalStation.accessCenter.isAlreadyRental(cardNumber)) {
                rentalStation.rentalBike(cardNumber);
                rentalStation.operatorRent.standUnlock();
                rentalStation.operatorRent.showTakeBike();
                System.out.println("情境提示：取車, 請以<ENTER>模擬");
                scan.nextLine();
                rentalStation.showRentResult(rentalStation.RC_RENTAL_SUCCESS);
            }
            else {
                rentalStation.showRentResult(rentalStation.RC_RENT_IN_LEASE);
            }
        }
        else {
            rentalStation.showRentResult(rentalStation.RC_CARD_INVALID);
        }

        // for debug
        System.out.println("\n< DATABASE >");
        rentalStation.accessCenter.db.dumpDBTable();
    }
}
