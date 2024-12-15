import java.util.Scanner;

public class RegisterAccount implements IStrategy {

    public static String location = "輔仁";

    public void execute() {
        AccountStation accountStation = new AccountStation(location);
        Scanner scan = new Scanner(System.in);
        String cardnumber;

        System.out.println("註冊功能請輸入１, 註消功能請輸入２, 卡片綁定請輸入３, 卡片解綁請輸入４");
        int func = scan.nextInt();

        if (func == 1) {
            accountStation.operatorReg.showAddInfo(0); //顯示 輸入會員資料
            //輸入 姓名 電話 密碼
            String name = scan.next();
            String phone = scan.next();
            String password = scan.next();
            accountStation.registerAccount(name, phone, password); //這裡也會判斷資料正確性

            //感應悠遊卡
            accountStation.operatorReg.showCardTap();
            cardnumber = accountStation.operatorReg.manualPin();

            //綁定悠遊卡
            if (accountStation.checkCard(cardnumber) == false) {
                if (scan.nextInt() == 1) {
                    accountStation.cardBinding(name, cardnumber);
                    accountStation.operatorReg.showCardFinish();
                }
            }
        }
        else if (func == 2) {
            accountStation.operatorReg.showAddInfo(2); //顯示 輸入會員資料

            //輸入 姓名 電話 密碼
            String name = scan.next();
            //String phone = scan.next();
            String password = scan.next();
            accountStation.unregisterAccount(name, password);
        }
        else if (func == 3) {
            accountStation.operatorReg.showAddInfo(3); //顯示 輸入會員資料

            //輸入 姓名 電話 密碼
            String name = scan.next();
            //String phone = scan.next();
            String cardNumber = scan.next();
            accountStation.cardBinding(name, cardNumber);

        }
        else if (func == 4) {
            accountStation.operatorReg.showAddInfo(1); //顯示 輸入會員資料

            //輸入 姓名 電話 密碼
            String name = scan.next();
            //String phone = scan.next();
            //String password = scan.next();
            accountStation.cardUnbind(name);

        }

        // for debug
        //System.out.println("\n< DATABASE >");
        //accountStation.accessCenter.db.dumpDBTable();

    }
}
