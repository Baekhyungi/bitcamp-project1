package bitcamp.project1;

import bitcamp.project1.command.Expenditure;
import bitcamp.project1.command.Income;
import bitcamp.project1.command.Total;
import bitcamp.project1.util.Prompt;

public class App {

    public static void main(String[] args) {
        Prompt prompt = new Prompt();
        Income income = new Income();
        Expenditure expenditure = new Expenditure();
        Total total = new Total(income, expenditure);

        while (true) {
            System.out.println("1. 수입 2. 지출 3. 총합 4. 종료");
            int choice = prompt.getInt("메인> ");

            switch (choice) {
                case 1:
                    income.manage(prompt);
                    break;
                case 2:
                    expenditure.manage(prompt);
                    break;
                case 3:
                    total.showTotal();
                    break;
                case 4:
                    System.out.println("프로그램을 종료합니다.");
                    return;
                default:
                    System.out.println("유효한 메뉴 번호가 아닙니다.");
            }
        }
    }
}