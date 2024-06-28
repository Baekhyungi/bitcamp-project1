package bitcamp.project1;

import bitcamp.project1.command.TransactionCommand;
import bitcamp.project1.util.Prompt;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class App {

    private static final String[] MAIN_MENUS = {"수입", "지출", "합계", "종료"};
    private static final String[][] SUB_MENUS = {
            {"등록", "목록", "조회", "변경", "삭제"},
    };

    private final TransactionCommand incomeCommand = new TransactionCommand();
    private final TransactionCommand expenditureCommand = new TransactionCommand();

    public static void main(String[] args) {
        new App().execute();
    }

    void execute() {
        printMenu();

        String command;
        while (true) {
            try {
                command = Prompt.input("메인>");

                if ("menu".equals(command)) {
                    printMenu();
                } else {
                    int menuNo = Integer.parseInt(command);
                    String menuTitle = getMenuTitle(menuNo, MAIN_MENUS);
                    if (menuTitle == null) {
                        System.out.println("유효한 메뉴 번호가 아닙니다.");
                    } else if ("종료".equals(menuTitle)) {
                        break;
                    } else if ("합계".equals(menuTitle)) {
                        showTotalIncomeAndExpense();
                    } else {
                        processMenu(menuTitle, SUB_MENUS[0]);
                    }
                }
            } catch (NumberFormatException ex) {
                System.out.println("숫자로 메뉴 번호를 입력하세요.");
            }
        }

        System.out.println("종료합니다.");

        Prompt.close();
    }

    void showTotalIncomeAndExpense() {
        System.out.println("수입/지출 내역을 출력합니다.");
        System.out.println("번호 구분 작성일 카테고리 내용 금액");
        incomeCommand.showSummary();
        expenditureCommand.showSummary();
        System.out.println("--------------------------------");

        int totalIncome = incomeCommand.calculateTotalAmount();
        int totalExpense = expenditureCommand.calculateTotalAmount();
        int totalAmount = totalIncome - totalExpense;

        DecimalFormat formatter = new DecimalFormat("#,###", new DecimalFormatSymbols(Locale.getDefault()));

        System.out.println("총 수입: " + formatter.format(totalIncome) + "원");
        System.out.println("총 지출: " + formatter.format(totalExpense) + "원");
        System.out.println("합계: " + formatter.format(totalAmount) + "원");
    }

    void printMenu() {
        String boldAnsi = "\033[1m";
        String redAnsi = "\033[31m";
        String resetAnsi = "\033[0m";

        String appTitle = "[가계부 팀프로젝트]";
        String line = "----------------------------------";

        System.out.println(boldAnsi + line + resetAnsi);
        System.out.println(boldAnsi + appTitle + resetAnsi);

        for (int i = 0; i < MAIN_MENUS.length; i++) {
            if ("종료".equals(MAIN_MENUS[i])) {
                System.out.printf("%s%d. %s%s\n", (boldAnsi + redAnsi), (i + 1), MAIN_MENUS[i], resetAnsi);
            } else {
                System.out.printf("%d. %s\n", (i + 1), MAIN_MENUS[i]);
            }
        }

        System.out.println(boldAnsi + line + resetAnsi);
    }

    void printSubMenu(String menuTitle, String[] menus) {
        System.out.printf("[%s]\n", menuTitle);
        for (int i = 0; i < menus.length; i++) {
            System.out.printf("%d. %s\n", (i + 1), menus[i]);
        }
        System.out.println("9. 이전");
    }

    boolean isValidateMenu(int menuNo, String[] menus) {
        return menuNo >= 1 && menuNo <= menus.length;
    }

    String getMenuTitle(int menuNo, String[] menus) {
        return isValidateMenu(menuNo, menus) ? menus[menuNo - 1] : null;
    }

    void processMenu(String menuTitle, String[] menus) {
        printSubMenu(menuTitle, menus);
        while (true) {
            String command = Prompt.input(String.format("메인/%s>", menuTitle));
            if ("menu".equals(command)) {
                printSubMenu(menuTitle, menus);
                continue;
            } else if ("9".equals(command)) {
                break;
            }

            try {
                int menuNo = Integer.parseInt(command);
                String subMenuTitle = getMenuTitle(menuNo, menus);
                if (subMenuTitle == null) {
                    System.out.println("유효한 메뉴 번호가 아닙니다.");
                } else {
                    switch (menuTitle) {
                        case "수입":
                        case "지출":
                            executeTransactionCommand(menuTitle, subMenuTitle);
                            break;
                        default:
                            System.out.printf("%s 메뉴의 명령을 처리할 수 없습니다.\n", menuTitle);
                    }
                }
            } catch (NumberFormatException ex) {
                System.out.println("숫자로 메뉴 번호를 입력하세요.");
            }
        }
    }

    void executeTransactionCommand(String type, String command) {
        switch (type) {
            case "수입":
                incomeCommand.executeTransactionCommand(type, command);
                break;
            case "지출":
                expenditureCommand.executeTransactionCommand(type, command);
                break;
        }
    }

}
