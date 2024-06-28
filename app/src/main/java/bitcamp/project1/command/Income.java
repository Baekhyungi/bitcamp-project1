package bitcamp.project1.command;

import bitcamp.project1.util.Prompt;
import bitcamp.project1.vo.Item;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class Income {

    private ArrayList<Item> incomes = new ArrayList<>();

    public void manage(Prompt prompt) {
        while (true) {
            System.out.println("\n[수입]");
            System.out.println("1. 등록 2. 목록 3. 조회 4. 변경 5. 삭제 9. 이전");

            int choice = prompt.getInt("메인/수입> ");

            switch (choice) {
                case 1:
                    addIncome(prompt);
                    break;
                case 2:
                    listIncomes();
                    break;
                case 3:
                    viewIncomes(prompt);
                    break;
                case 4:
                    updateIncome(prompt);
                    break;
                case 5:
                    deleteIncome(prompt);
                    break;
                case 9:
                    return;
                default:
                    System.out.println("잘못된 선택입니다.");
            }
        }
    }

    private void addIncome(Prompt prompt) {
        String dateString = prompt.getString("날짜 (YYYY-MM-DD): ");
        LocalDate date;
        try {
            date = LocalDate.parse(dateString);
        } catch (DateTimeParseException e) {
            System.out.println("잘못된 날짜 형식입니다. 날짜를 다시 입력해 주세요.");
            return;
        }
        String category = prompt.getString("카테고리: ");
        int amount = prompt.getInt("금액: ");
        String description = prompt.getString("내용: ");

        incomes.add(new Item(date, category, amount, description));
        System.out.println("수입이 추가되었습니다.");
    }

    private void listIncomes() {
        if (incomes.isEmpty()) {
            System.out.println("등록된 수입이 없습니다.");
            return;
        }
        for (int i = 0; i < incomes.size(); i++) {
            System.out.println(i + 1 + ". " + incomes.get(i));
        }
    }

    private void viewIncomes(Prompt prompt) {
        if (incomes.isEmpty()) {
            System.out.println("조회할 수입이 없습니다.");
            return;
        }

        listIncomes();

        int index = prompt.getInt("조회할 항목 번호: ") - 1;
        if (index < 0 || index >= incomes.size()) {
            System.out.println("잘못된 번호입니다.");
            return;
        }

        Item item = incomes.get(index);
        System.out.println("===== 수입 상세 정보 =====");
        System.out.printf("날짜: %s\n", item.getDate());
        System.out.printf("카테고리: %s\n", item.getCategory());
        System.out.printf("금액: %,d원\n", item.getAmount());
        System.out.printf("내용: %s\n", item.getDescription());
    }

    private void updateIncome(Prompt prompt) {
        listIncomes();
        if (incomes.isEmpty()) {
            System.out.println("변경할 수입이 없습니다.");
            return;
        }
        int index = prompt.getInt("변경할 항목 번호: ") - 1;
        if (index < 0 || index >= incomes.size()) {
            System.out.println("잘못된 번호입니다.");
            return;
        }

        String dateString = prompt.getString("새 날짜 (YYYY-MM-DD): ");
        LocalDate date;
        try {
            date = LocalDate.parse(dateString);
        } catch (DateTimeParseException e) {
            System.out.println("잘못된 날짜 형식입니다. 날짜를 다시 입력해 주세요.");
            return;
        }
        String category = prompt.getString("새 카테고리: ");
        int amount = prompt.getInt("새 금액: ");
        String description = prompt.getString("새 내용: ");

        incomes.set(index, new Item(date, category, amount, description));
        System.out.println("수입이 변경되었습니다.");
    }

    private void deleteIncome(Prompt prompt) {
        listIncomes();
        if (incomes.isEmpty()) {
            System.out.println("삭제할 수입이 없습니다.");
            return;
        }
        int index = prompt.getInt("삭제할 항목 번호: ") - 1;
        if (index < 0 || index >= incomes.size()) {
            System.out.println("잘못된 번호입니다.");
            return;
        }

        incomes.remove(index);
        System.out.println("수입이 삭제되었습니다.");
    }

    public int getTotal() {
        return incomes.stream().mapToInt(Item::getAmount).sum();
    }
}