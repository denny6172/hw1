import java.util.Scanner;

public class Main_Return {

    public static String location = "輔仁";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        RentalStation rentalStation = new RentalStation(location);

        System.out.println("<<<  Return 歸還  >>>");

        System.out.println("預設條件：請優先預設BIKE車號及地點  <空白分隔>");
        int bikeNum = sc.nextInt();
        String location = sc.nextLine().strip();
        rentalStation.setNumber(bikeNum);
        rentalStation.setLocation(location);

        System.out.println("謝謝!  BIKE車號：" + bikeNum + ", 地點：" + location + "\n");

        System.out.println("情境提示：將車推進停車卡槽  請以<ENTER>模擬");
        sc.nextLine();
        rentalStation.operatorRent.isBikeExist();

        rentalStation.operatorRent.showTapCard(1);
        System.out.println("情境提示：將卡片放置於感應區感應  <手動輸入卡號>");
        String cardNumber = sc.nextLine();
        rentalStation.operatorRent.readCardNumber(cardNumber);

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
        DatabaseConnect db = new DatabaseConnect();
        db.dumpDBTable();
    }
}
