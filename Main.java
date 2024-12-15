import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Company company = new Company();
        Scanner scan = new Scanner(System.in);

        System.out.println(company.getName());

        System.out.println("功能選擇 [1]帳號申辦, [2]租賃查詢, [3]租借單車, [4]歸還單車");
        System.out.print("請輸入[1~4]：");
        int func = scan.nextInt();

        if (func == 1)
            company.choiceStrategy(new RegisterAccount());
        else if (func == 2)
            company.choiceStrategy(new QueryBike());
        else if (func == 3)
            company.choiceStrategy(new RentalBike());
        else if (func == 4)
            company.choiceStrategy(new ReturnBike());

        company.service();

    }

}
