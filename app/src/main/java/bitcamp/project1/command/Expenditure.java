package bitcamp.project1.command;

import bitcamp.project1.vo.Item;
import bitcamp.project1.util.Prompt;

import java.util.ArrayList;
import java.time.LocalDate;
public class Expenditure {

    private ArrayList<Item> expenditures = new ArrayList<>();

    public void manage(Prompt prompt) {
        while (true) {
            System.out.println("\n[지출]");
            System.out.println("1. 등록 2. 목록 3. 변경 4. 삭제 9. 이전");

            int choice = prompt.getInt("메인/지출> ");

            switch (choice) {
                case 1:
                    addExpenditure(prompt);
                    break;
                case 2:
                    listExpenditures();
                    break;
                case 3:
                    updateExpenditure(prompt);
                    break;
                case 4:
                    deleteExpenditure(prompt);
                    break;
                case 9:
                    return;
                default:
                    System.out.println("잘못된 선택입니다.");
            }
        }
    }

    private void addExpenditure(Prompt prompt) {
        LocalDate date = prompt.getDate("날짜 (YYYY-MM-DD): ");
        String category = prompt.getString("카테고리: ");
        int amount = prompt.getInt("금액: ");
        String description = prompt.getString("내용: ");

        expenditures.add(new Item(date, category, amount, description));
        System.out.println("지출이 추가되었습니다.");
    }

    private void listExpenditures() {
        if (expenditures.isEmpty()) {
            System.out.println("등록된 지출이 없습니다.");
            return;
        }
        for (int i = 0; i < expenditures.size(); i++) {
            System.out.println(i + 1 + ". " + expenditures.get(i));
        }
    }

    private void updateExpenditure(Prompt prompt) {
        listExpenditures();
        if (expenditures.isEmpty()){
            System.out.println("변경할 지출이 없습니다.");
        }
        int index = prompt.getInt("변경할 항목 번호: ") - 1;
        if (index < 0 || index >= expenditures.size()) {
            System.out.println("잘못된 번호입니다.");
            return;
        }

        LocalDate date = prompt.getDate("새 날짜 (YYYY-MM-DD): ");
        String category = prompt.getString("새 카테고리: ");
        int amount = prompt.getInt("새 금액: ");
        String description = prompt.getString("새 내용: ");

        expenditures.set(index, new Item(date, category, amount, description));
        System.out.println("지출이 변경되었습니다.");
    }

    private void deleteExpenditure(Prompt prompt) {
        listExpenditures();
        if (expenditures.isEmpty()){
            System.out.println("삭제할 지출이 없습니다.");
        }
        int index = prompt.getInt("삭제할 항목 번호: ") - 1;
        if (index < 0 || index >= expenditures.size()) {
            System.out.println("잘못된 번호입니다.");
            return;
        }

        expenditures.remove(index);
        System.out.println("지출이 삭제되었습니다.");
    }

    public int getTotal() {
        return expenditures.stream().mapToInt(item -> item.amount).sum();
    }
}
