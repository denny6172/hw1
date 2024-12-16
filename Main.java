/* 413226087 李柏翰 */
/* 413085043 謝慶章 */

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Context context = new Context();
        Scanner scan = new Scanner(System.in);

        System.out.println(context.getName());

        System.out.println("功能選擇 [1]帳號申辦, [2]租賃查詢, [3]租借單車, [4]歸還單車");
        System.out.print("請輸入[1~4]：");
        int func = scan.nextInt();

        if (func == 1)
            context.choiceStrategy(new RegisterAccount());
        else if (func == 2)
            context.choiceStrategy(new QueryBike());
        else if (func == 3)
            context.choiceStrategy(new RentalBike());
        else if (func == 4)
            context.choiceStrategy(new ReturnBike());

        context.service();

    }

}
