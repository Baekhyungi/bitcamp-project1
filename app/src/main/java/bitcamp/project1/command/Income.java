package bitcamp.project1.command;

import bitcamp.project1.util.Prompt;
import bitcamp.project1.vo.Item;
import java.util.ArrayList;
import java.time.LocalDate;

public class Income {

    private ArrayList<Item> incomes = new ArrayList<>();

    public void manage(Prompt prompt) {
        while (true) {
            System.out.println("\n[수입]");
            System.out.println("1. 등록 2. 목록 3. 변경 4. 삭제 9. 이전");
            int choice = prompt.getInt("메인/수입> ");

            switch (choice) {
                case 1:
                    addIncome(prompt);
                    break;
                case 2:
                    listIncomes();
                    break;
                case 3:
                    updateIncome(prompt);
                    break;
                case 4:
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
        LocalDate date = prompt.getDate("날짜 (YYYY-MM-DD): ");
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

    private void updateIncome(Prompt prompt) {
        listIncomes();
        if (incomes.isEmpty()){
            System.out.println("변경할 수입이 없습니다.");
        }
        int index = prompt.getInt("변경할 항목 번호: ") - 1;
        if (index < 0 || index >= incomes.size()) {
            System.out.println("잘못된 번호입니다.");
            return;
        }

        LocalDate date = prompt.getDate("새 날짜 (YYYY-MM-DD): ");
        String category = prompt.getString("새 카테고리: ");
        int amount = prompt.getInt("새 금액: ");
        String description = prompt.getString("새 내용: ");

        incomes.set(index, new Item(date, category, amount, description));
        System.out.println("수입이 변경되었습니다.");
    }

    private void deleteIncome(Prompt prompt) {
        listIncomes();
        if (incomes.isEmpty()){
            System.out.println("삭제할 수입이 없습니다.");
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
        return incomes.stream().mapToInt(item -> item.amount).sum();
    }
}
